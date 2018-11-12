///******************************************************************************
//* Copyright (c) 2013 by Cisco Systems, Inc. All rights reserved.
//*
// * This software contains proprietary information which shall not be reproduced
//* or transferred to other documents and shall not be disclosed to others or
//* used for manufacturing or any other purpose without prior permission of Cisco
//* Systems.
//*
// *****************************************************************************/
// 
//package com.cisco.xmp.config.impl.service;
// 
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ExecutionException;
// 
//import org.apache.commons.lang.StringUtils;
//import org.hibernate.FlushMode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.transaction.TransactionStatus;
// 
//import com.cisco.xmp.config.impl.executor.asyncOperation.base.AsyncOperationFutureImpl;
//import com.cisco.xmp.config.impl.executor.configAsyncEngine.AsyncConfigEngine;
//import com.cisco.xmp.config.impl.executor.configAsyncEngine.AsyncConfigThreadPoolConfigurer;
//import com.cisco.xmp.config.impl.executor.configAsyncOperation.AsyncConfigDevice;
//import com.cisco.xmp.config.impl.executor.configAsyncOperation.AsyncConfigDevice.FeatureToAllowedToVisitModelList;
//import com.cisco.xmp.config.impl.executor.configAsyncOperation.AsyncConfigRequest;
//import com.cisco.xmp.config.impl.log.ConfigLoggingUtil;
//import com.cisco.xmp.config.impl.service.util.ConfigServiceUtil;
//import com.cisco.xmp.config.impl.service.util.CustomTagReader;
//import com.cisco.xmp.config.impl.service.util.ExcludedAssociations;
//import com.cisco.xmp.config.impl.service.util.IntentObjectCloner;
//import com.cisco.xmp.config.impl.service.util.ObjectCloner;
//import com.cisco.xmp.config.impl.service.util.ObjectCloner.InstancePair;
//import com.cisco.xmp.config.intf.exception.XMPConfigHookException;
//import com.cisco.xmp.config.intf.exception.XMPValidationException;
//import com.cisco.xmp.config.intf.hook.ConfigHookContext;
//import com.cisco.xmp.config.intf.hook.IConfigHook;
//import com.cisco.xmp.config.intf.service.AbstractConfigSpecification;
//import com.cisco.xmp.config.intf.service.BulkCommandsTemplate;
//import com.cisco.xmp.config.intf.service.BulkConfigSpecification;
//import com.cisco.xmp.config.intf.service.ConfigDeviceSettings.RollbackMode;
//import com.cisco.xmp.config.intf.service.ConfigOperation;
//import com.cisco.xmp.config.intf.service.ConfigServiceSettings;
//import com.cisco.xmp.config.intf.service.ConfigServiceSettings.DeploymentMode;
//import com.cisco.xmp.config.intf.service.ConfigSpecification;
//import com.cisco.xmp.config.intf.service.ConfigUnit;
//import com.cisco.xmp.config.intf.service.ConnectedModelGraphSpecification;
//import com.cisco.xmp.config.intf.service.FeatureConfigSpecification;
//import com.cisco.xmp.config.intf.service.IBulkConfigService;
//import com.cisco.xmp.config.intf.service.IBulkConfigServiceFuture;
//import com.cisco.xmp.config.intf.service.IBulkConfigServiceResult;
//import com.cisco.xmp.i18nl10nexception.XMPCheckedException;
//import com.cisco.xmp.inventory.ice.InventoryCollectorEngine;
//import com.cisco.xmp.model.framework.base.InstanceImpl;
// 
// 
///**
//* Bulk config Service for configuring multiple features in a single
//* transaction. Offers functionality where provisioning a service requires
//* configuring multiple features together in a single transaction. Currently
//* used exclusively for Prime Fullfilment 7. The idea is to collect several
//* command features and feed it into XDE NOM a single time to collect an
//* efficient NOM diff. Once an aggregated diff is collected it can be applied to
//* the device in a single call.
//*
// *
// *
// *
// * Basically the lifecycle flow is as follows:
//*
// * 1) Service provisioning calls MBC with a set of feature activation requests
//* for a given device. In this single call the feature requests can be a mixture
//* of create and delete requests. Service provisioning can also optionally pass
//* a set of CLI templates. These templates are somehow marked to determine where
//* in the feature execution flow they should be inserted, and whether or not
//* they should use NOM.
//*
// * 2) MBC performs validation and creates/deletes the model where appropriate.
//*
// * 3) MBC calls the XDE function
//* xmp_device_update_utility\parseDeviceConfigurations.xde . This procedure will
//* fetch the device configuration, and parse it into two NOM structures. One for
//* changes applied by the templates/features and one left unchanged that will be
//* used for the final diff.
//*
// * 4) MBC calls the XDE function
//* xmp_config_parser\parseConfigGivenConfigParser.xde for each prepend NOM and
//* prepend non-NOM templates.
//*
// * 5) MBC calls all of the feature configuration implementations (Java or XDE)
//* for the features specified in the bulk call. Each feature is checked to see
//* what number of arguments they take. If they take the same number of arguments
//* as XMP currently does (i.e. just the device and model arguments) then the CLI
//* feature is executed normally and MBC applies the resulting config to NOM
//* (using the xmp_config_parser\parseConfigGivenConfigParser.xde function). If
//* the feature takes more arguments (i.e. the device, model and configuration
//* arguments) then the feature is also passed the device configure/NOM and is
//* expected to update it.
//*
// * 6) MBC calls the XDE function
//* xmp_config_parser\parseConfigGivenConfigParser.xde for each postpend NOM and
//* postpend non-NOM templates.
//*
// * 7) MBC calls the XDE function
//* xmp_device_update_utility\generateDeviceConfiglets.xde to create the minimal
//* diff/ordered CLI configlet.
//*
// * 8) MBC calls the XDE function xmp_push_to_device_utility\pushConfiglet.xde
//* that takes the XML document and interprets it to form the actual PAL call.
//*
// * 10) MBC completes and sends results to caller. (Note: ignoring rollback case,
//* which should be pretty much the same).
//*
// *
// *
// * @author jasmbhat
//*
// */
//public class BulkConfigService extends AbstractConfigService implements
//               IBulkConfigService, ApplicationContextAware {
// 
//        @Autowired
//        private CustomTagReader customTagReader;
//       
//        @Autowired
//        protected AsyncConfigThreadPoolConfigurer asyncConfigThreadPoolConfigurer;
//       
//        private AsyncConfigEngine asyncConfigEngine;
// 
//        public BulkConfigService() {
//                              
//        }
//       
//        @Override
//        public void setLog(ConfigLoggingUtil log) {
//               super.setLog(log);
//               asyncConfigEngine = new AsyncConfigEngine();
//               asyncConfigEngine.start(getLog(), asyncConfigThreadPoolConfigurer);
//        }
//       
//        public AsyncConfigEngine getAsyncConfigEngine() {
//               return asyncConfigEngine;
//        }
//       
//        public CustomTagReader getCustomTagReader() {
//               return customTagReader;
//        }
//              
//        /*
//        * (non-Javadoc)
//        *
//     * Configure multiple features on a device, deploy to a device only or preview commands using the
//     * provided {@link BulkConfigSpecification} object.
//     *
//     * @param bulkConfigSpecification the specification for the bulk config service beign carried out
//     * @param configServiceSettings service settings for the bulk config operation to perform
//     *
//     * @return IConfigServiceFuture the returned future
//     */
//        @Override
//        public IBulkConfigServiceFuture saveAndDeployAsync(BulkConfigSpecification bulkConfigSpec, ConfigServiceSettings configServiceSettings) {
//               List<BulkConfigSpecification> bulkConfigSpecs = new ArrayList<BulkConfigSpecification>();
//               bulkConfigSpecs.add(bulkConfigSpec);
//               log.debug("Received in saveAndDeployAsync, invoking saveAndDeployAsyncAll() internally ");
//               return saveAndDeployAsyncAll(bulkConfigSpecs, configServiceSettings);
//        }
//       
//        @Override
//        public IBulkConfigServiceFuture saveAndDeployAsyncAll(List<BulkConfigSpecification> bulkConfigSpecs, ConfigServiceSettings configServiceSettings) {
//              
//               log.info("Received in saveAndDeployAsyncAll(), configServiceSettings : " + configServiceSettings);
//               log.info("Received in saveAndDeployAsyncAll(), bulkConfigSpecs : " + bulkConfigSpecs);
//                              
//               // Create request
//               int priorityBand = 0; // Ready for when we need it
//               AsyncConfigRequest request = new AsyncConfigRequest(this, bulkConfigSpecs, configServiceSettings);
//              
//               AsyncOperationFutureImpl<XMPCheckedException, List<IBulkConfigServiceResult>> asyncFuture =
//                               new AsyncOperationFutureImpl<XMPCheckedException, List<IBulkConfigServiceResult>>(request);
//               ConfigServiceFuture future = new ConfigServiceFuture(asyncFuture);
//              
//               // Schedule request for execution
//               asyncConfigEngine.getMainRequestScheduler().scheduleOperation(
//                               request, asyncFuture, request, priorityBand);
//              
//               return future;
//        }
// 
//        /*
//        * (non-Javadoc)
//        *
//         * @see
//        * com.cisco.xmp.config.intf.service.IBulkConfigService#saveAndDeploy(com
//        * .cisco.xmp.config.intf.service.BulkConfigSpecification)
//        */
//        @Override
//        public String saveAndDeploy(BulkConfigSpecification bulkConfigSpec)
//                       throws XMPCheckedException {
//               try {
//               log.info("Received in saveAndDeploy(): " + bulkConfigSpec);
//               String commands = saveAndDeploySync(bulkConfigSpec,
//                               DeploymentMode.PreviewDeployAndSave, RollbackMode.getNone(), true);
//               return commands;
//               } catch (XMPCheckedException ex) {
//                       log.error("saveAndDeploy() finished with exception - " + ConfigServiceUtil.getStackTraceAsString(ex));
//                       throw ex;
//               }
//        }
// 
//        /*
//        * (non-Javadoc)
//        *
//         * @see com.cisco.xmp.config.intf.service.IBulkConfigService#deployOnly(com
//        * .cisco.xmp.config.intf.service.BulkConfigSpecification)
//        */
//        @Override
//        public String deployOnly(BulkConfigSpecification bulkConfigSpec)
//                       throws XMPCheckedException {
//               try {
//               log.debug("Received in deployOnly(): " + bulkConfigSpec);
//               String commands = saveAndDeploySync(bulkConfigSpec,
//                               DeploymentMode.PreviewDeploy, RollbackMode.getDefault(), true);
//               return commands;
//               } catch (XMPCheckedException ex) {
//                       log.error("deployOnly() finished with exception - " + ConfigServiceUtil.getStackTraceAsString(ex));
//                       throw ex;
//               }
//        }
// 
//        /*
//        * (non-Javadoc)
//        *
//         * @see
//        * com.cisco.xmp.config.intf.service.IBulkConfigService#previewGeneratedCommands
//        * (com.cisco.xmp.config.intf.service.BulkConfigSpecification)
//        */
//        @Override
//        public String previewGeneratedCommands(
//                       BulkConfigSpecification bulkConfigSpec) throws XMPCheckedException {
//               try {
//               log.info("Received in previewGeneratedCommands(): " + bulkConfigSpec);
//               String commands = saveAndDeploySync(bulkConfigSpec,
//                               DeploymentMode.Preview, RollbackMode.getDefault(), true);
//               return commands;
//               } catch (XMPCheckedException ex) {
//                       log.error("previewGeneratedCommands() finished with exception - " + ConfigServiceUtil.getStackTraceAsString(ex));
//                       throw ex;
//               }
//        }
//       
//        private String saveAndDeploySync(BulkConfigSpecification bulkConfigSpec, DeploymentMode deploymentMode,
//                       Set<RollbackMode> rollbackModes, boolean mbcBulkMode) throws XMPCheckedException {
//               IBulkConfigServiceFuture future = saveAndDeployAsync(
//                               bulkConfigSpec, new ConfigServiceSettings(deploymentMode, mbcBulkMode, true));
//              
//               log.debug("Received in saveAndDeploySync() :bulkConfigSpec"+bulkConfigSpec);
// 
//               try {
//                       String result = "";
//                       List<IBulkConfigServiceResult> futureResult = future.get();
//                       if (!futureResult.isEmpty()) result = futureResult.get(0).getConfiguration();
//                       return result;
//               } catch (ExecutionException ex) {
//                       Throwable e = ex;
//                       if (ex.getCause() != null) e = ex.getCause(); // The actual XMPCheckedException
//                       XMPCheckedException resultEx = asyncConfigEngine.getExceptionAdapter().
//                                      adaptException(e, "Failed to execute request");
//                       throw resultEx;
//               } catch (Exception e) {
//                       XMPCheckedException resultEx = asyncConfigEngine.getExceptionAdapter().
//                                      adaptException(e, "Failed to execute request");
//                       throw resultEx;
//               }
//        }
// 
//        /**
//        * Update inventory for all explicitly/implicitly affect entity objects.
//        *
//         * @param deviceId
//        * @param transactionId
//        * @param featureConfigSpec
//        * @param status
//        * @param save
//        * @param deploy
//        * @return
//        * @throws XMPCheckedException
//        */
//        @SuppressWarnings("unchecked")
//        public List<ConfigUnit>[] updateInventory(String deviceId,
//                       long transactionId, FeatureConfigSpecification featureConfigSpec,
//                       List<Set<String>> allowedToVisitModels, TransactionStatus status,
//                       boolean save, boolean deploy, Set<String> skipDeleteSet,
//                       Map<String, InstanceImpl> keyToOriginalObject,
//                       ExcludedAssociations excludeAssociations,
//                       boolean shouldValidateForConfigurable,
//                       List<IConfigHook> configHooks) throws XMPCheckedException {
// 
//               if (log.emitDebug()) {
//                       log.debug("Processing model : " + deviceId + " : " + featureConfigSpec);
//               }
//              
//               clearThreadLocals();
//              
//               List<ConfigUnit>[] configUnitsArray = initializeConfigUnitsArray(featureConfigSpec
//                               .getConnectedModelGraphSpecifications().length);
// 
//               ConnectedModelGraphSpecification[] connectedModelGraphSpecs = featureConfigSpec
//                               .getConnectedModelGraphSpecifications();
// 
//               // Calling Validation outside, as deviceId needed for acquiring lock,
//               // hence ConfigSpec needs to be validated.
//               // Perform validations (Required parameter checks and JSR-303 constraint
//               // checks)
//               // _validate(configSpec);
// 
//               // Save configuration
//               // Decompose passed-in connected model graphs and validate
//               configUnitsFromPreUpdateHandler
//                               .set((List<ConfigUnit>[]) new ArrayList[connectedModelGraphSpecs.length]);
//               for (int i = 0; i < connectedModelGraphSpecs.length; ++i) {
//                       configUnitsFromPreUpdateHandler.get()[i] = new ArrayList<ConfigUnit>();
//               }
//               configUnitsFromPreDeleteHandler
//                               .set((List<ConfigUnit>[]) new ArrayList[connectedModelGraphSpecs.length]);
//               for (int i = 0; i < connectedModelGraphSpecs.length; ++i) {
//                       configUnitsFromPreDeleteHandler.get()[i] = new ArrayList<ConfigUnit>();
//               }
//               objectsUnderCreation
//                               .set((List<Long>[]) new ArrayList[connectedModelGraphSpecs.length]);
//               for (int i = 0; i < connectedModelGraphSpecs.length; ++i) {
//                       objectsUnderCreation.get()[i] = new ArrayList<Long>();
//               }
//               processedObjectsByPreUpdateHandler
//                               .set((List<Long>[]) new ArrayList[connectedModelGraphSpecs.length]);
//               for (int i = 0; i < connectedModelGraphSpecs.length; ++i) {
//                       processedObjectsByPreUpdateHandler.get()[i] = new ArrayList<Long>();
//               }
//               processedObjectsByPreDeleteHandler
//                               .set((List<Long>[]) new ArrayList[connectedModelGraphSpecs.length]);
//               for (int i = 0; i < connectedModelGraphSpecs.length; ++i) {
//                       processedObjectsByPreDeleteHandler.get()[i] = new ArrayList<Long>();
//               }
//              
//               validateForConfigurable.set(shouldValidateForConfigurable);
//                              
//               Exception saveAndDeleteException = null;
//               int currentlyAllowedToVisitModelsIndex = 0;
//               try {
//                       currentDeviceId.set(deviceId);
//                       for (int i = 0; i < featureConfigSpec
//                                      .getConnectedModelGraphSpecifications().length; i++) {
//                               ConnectedModelGraphSpecification connectedModelGraphSpec = featureConfigSpec
//                                              .getConnectedModelGraphSpecifications()[i];
//                               InstanceImpl connectedModelGraph = (InstanceImpl) connectedModelGraphSpec
//                                              .getConnectedModelGraph();
//                               ConfigOperation operation = connectedModelGraphSpec
//                                              .getOperation();
//                                                            
//                               ObjectCloner objectCloner = new ObjectCloner(log, persistenceService, persistenceFactory, excludeAssociations);
//                               IntentObjectCloner intentObjectCloner = new IntentObjectCloner(log, persistenceService);
//                               alreadyDeepInitializedObjects.set(objectCloner);
// 
//                               Set<String> currentlyAllowedToVisitModels = allowedToVisitModels
//                                              .get(currentlyAllowedToVisitModelsIndex);
//                               currentlyAllowedToVisitModelsIndex++;
//                               if ((ConfigOperation.CREATE == operation)
//                                              || (ConfigOperation.UPDATE == operation)) {
// 
//                                              // process decorators
//                                              decomposeConnectedModelGraph(deviceId,
//                                                             objectCloner, intentObjectCloner,
//                                                             currentlyAllowedToVisitModels,
//                                                             connectedModelGraph, new HashSet<String>(),
//                                                             configUnitsArray[i], keyToOriginalObject, save, false, true);
// 
//                                              convertDecoratorsToConfigUnits(deviceId,
//                                                             objectCloner, intentObjectCloner,
//                                                             connectedModelGraphSpec.getDecorators(),
//                                                             configUnitsArray[i], currentlyAllowedToVisitModels,
//                                                             keyToOriginalObject, save, false);
//                               } else {
//                                      configUnitsArray[i] = new ArrayList<ConfigUnit>();
// 
//                                      // process decorators
//                                      convertDecoratorsToConfigUnits(deviceId,
//                                                     objectCloner, intentObjectCloner,
//                                                      connectedModelGraphSpec.getDecorators(),
//                                                     configUnitsArray[i], currentlyAllowedToVisitModels,
//                                                     keyToOriginalObject, save, false);
// 
// 
//                                      // Set internal attributes, if any
//                                       setInternalAttributesRecursively(connectedModelGraph,
//                                                     currentlyAllowedToVisitModels);
// 
//                                      /*
//                                      * Get current model for intended model from inventory.
//                                      * Populate ConfigUnit with intendedModel, currentModel and
//                                      * operation. Prevent currentModel from being overwritten by
//                                      * subsequent changes to the database. If current model is
//                                      * null, that means object doesn't exist in the inventory.
//                                      * In that case, do not add this object to intended model.
//                                      */
// 
//                                      InstancePair instancePair = objectCloner.cloneAndInitializeObjectPair(connectedModelGraph, true);                               
//                                      if (instancePair != null) {
//                                              configUnitsArray[i].add(new ConfigUnit(
//                                                              instancePair.originalObject,
//                                                             instancePair.clonedObject,
//                                                             ConfigOperation.DELETE, null));
//                                      } else {
//                                              log.warning("Following object with DELETE intent doesn't exist in the inventory database and hence will not be added to the intended model - "
//                                                             + connectedModelGraph);
//                                      }
// 
//                               }
// 
//                               // Mark root node of passed in CMG
//                               if (configUnitsArray[i].size() > 0) {
//                                      configUnitsArray[i].get(0).setRootNode(true);
//                               }
//                              
//                               // Added for debugging
//                               logGraphDecomposeDataForIndex(connectedModelGraphSpecs,
//                                              configUnitsArray, i, System.currentTimeMillis());
// 
//                               // Objects in original CMG
//                               Set<String> originalObjectKeys = new HashSet<String>();
//                               for (ConfigUnit cu : configUnitsArray[i]) {
//                                       originalObjectKeys.add(getModelKey(cu.getIntendedModel()));
//                               }
//                              
//                               // Execute preSave hooks, if any
//                               if (configHooks != null) {
//                                      // So the hooks can use the PreDelete/PreUpdate handler
//                                      configOperation.set(ConfigOperation.CREATE);
//                                      for (IConfigHook configHook : configHooks) {
//                                              try {
//                                                     long beforeTs = System.currentTimeMillis();
//                                                     log.debug("updateInventory calling presavehook " + configHook );
//                                                     configHook.preSave(new ConfigHookContext(deviceId,
//                                                                     featureConfigSpec.getFeature(),
//                                                                     configUnitsArray));
//                                                      log.perf((System.currentTimeMillis() - beforeTs)
//                                                                     / 1000.0 + " seconds - preSave hook in "
//                                                                     + configHook.getClass().getName());
//                                              } catch (XMPConfigHookException e) {
//                                                     throw log.getXMPConfigCheckedException(
//                                                                     "CONFIG_052", e, new Object[] {});
//                                              }
//                                      }
//                               }
// 
//                               // Store connected model graph index in a ThreadLocal variable
//                               connectedModelGraphIndex.set(i);
//                               // Execute in this order - CREATE, UPDATE and DELETE to prevent
//                               // org.hibernate.TransientObjectException
// 
//                               for (ConfigUnit configUnit : configUnitsArray[i]) {
//                                      if (configUnit.getOperation() == ConfigOperation.UPDATE) {
//                                              PreSaveAspect.updatedEntities.get().add(configUnit.getIntendedModel().getInstanceId());
//                                      }
//                               }
//                              
//                               // Create operation
//                               for (ConfigUnit configUnit : configUnitsArray[i]) {
//                                      // Store the operation in a ThreadLocal variable
//                                      configOperation.set(configUnit.getOperation());
//                                      performDbOp(deviceId, configUnit,
//                                                     ConfigOperation.CREATE, save, deploy, false, status);
//                                              if (configUnit.getOperation() == ConfigOperation.CREATE) {
//                                                     updateOriginalObject(configUnit, keyToOriginalObject);
//                                              }
//                               }
//                               // Update operation
//                               for (ConfigUnit configUnit : configUnitsArray[i]) {
//                                      // Store the operation in a ThreadLocal variable
//                                      configOperation.set(configUnit.getOperation());
//                                      performDbOp(deviceId, configUnit, ConfigOperation.UPDATE,
//                                                     save, deploy, false, status);
//                                      if (configUnit.getOperation() == ConfigOperation.UPDATE) {
//                                              updateOriginalObject(configUnit, keyToOriginalObject);
//                                      }
//                               }
// 
//                               // In preSave hook, some callers (e.g. AEMS) augment initial
//                               // intent with additional objects. Mark root nodes for these
//                               // additional objects.
//                               for (ListIterator<ConfigUnit> it = configUnitsArray[i]
//                                              .listIterator(); it.hasNext();) {
//                                      ConfigUnit cu = it.next();
//                                      if (!originalObjectKeys.contains(getModelKey(cu
//                                                     .getIntendedModel()))) {
//                                              log.debug("preSave hook added: " + cu.getOperation()
//                                                             + "/" + cu.getIntendedModel());
//                                              cu.setRootNode(true);
// 
//                                              // For new objects with DELETE intent, set current model
//                                              if (ConfigOperation.DELETE == cu.getOperation()) {
//                                                     InstancePair instancePair = objectCloner.cloneAndInitializeObjectPair(cu
//                                                                     .getIntendedModel(), true);   
//                                                     if (instancePair != null) {
//                                                             cu.setIntendedModel(instancePair.originalObject);
//                                                             cu.setCurrentModel(instancePair.clonedObject);
//                                                     }
//                                              }
//                                      }
//                               }
// 
//                               // GC: Removed wrapped exception from here
//                               // Its not here in "normal" config and we can't have it any more
//                                      try {
//                                              long deleteTs = System.currentTimeMillis();
//                                              for (ConfigUnit configUnit : configUnitsArray[i]) {
//                                                     // Store the operation in a ThreadLocal variable
//                                                      configOperation.set(configUnit.getOperation());
//                                                     log.debug("updateInventory performDbOp ConfigOperation.DELETE saveAndDeploy");
//                                                    
//                                                     // Get the intendedModel for the configUnit and its
//                                                     // business key
//                                                     InstanceImpl intendedModel = configUnit.getIntendedModel();
//                                                     String modelKey = getModelKey(intendedModel);
//                                                    
//                                                     // If the business key is not null and contained in the skipDeleteSet,
//                                                     // skip the delete operation
//                                                     if ((modelKey.length() > 0) && !(skipDeleteSet.contains(modelKey))){
//                                                             performDbOp(deviceId, configUnit,
//                                                                             ConfigOperation.DELETE, save, deploy, false, status);
//                                                     }
//                                              }
//                                              status.flush();
//                                              log.debug("Flush Executed Successfully. ");
//                                              log.perf((System.currentTimeMillis() - deleteTs)
//                                                             / 1000.0 + " seconds - deleted object");
//                                      } catch (Exception e) {
//                                              log.error("updateInventory performDbOp ConfigOperation.DELETE exception " + e);
//                                              if (e instanceof XMPCheckedException) {
//                                                     throw (XMPCheckedException) e;
//                                              } else {
//                                                     throw log.getXMPConfigCheckedException(
//                                                                     "CONFIG_003", e, new Object[] {});
//                                              }
//                                      } finally {
//                                              if (saveAndDeleteException == null) {
//                                                     log.debug("Save and Deploy Commit Executed Successfully ");
//                                              }
//                                      }
//              
//                               /*
//                               * Flush the underlying Hibernate session so that objects
//                               * persisted for this connected model graph are synchronized
//                               * with the database.
//                               */
//                               log.debug("updateInventory performDbOp status.flush");
//                               status.flush();
// 
//                               /*
//                               * Include associated model objects populated by
//                               * PreDeleteHandler to maintain data integrity. These models
//                               * must have been populated (if any) by PreDeleteHandler at this
//                               * stage.
//                               */
//                               List<ConfigUnit> configUnitsPreDelete = configUnitsFromPreDeleteHandler.get()[i];
//                               log.debug("updateInventory configUnitsPreDelete "+ configUnitsPreDelete);
//                               mergeAndUpdateFromHandler(configUnitsPreDelete, configUnitsArray, i);
// 
//                               /*
//                               * Include associated model objects populated by
//                               * PreUpdateHandler to maintain data integrity. These models
//                               * must have been populated (if any) by PreUpdateHandler at this
//                               * stage.
//                               */
//                               List<ConfigUnit> configUnitsPreUpdate = configUnitsFromPreUpdateHandler.get()[i];
//                               log.debug("updateInventory configUnitsPreUpdate "+ configUnitsPreUpdate);
//                               mergeAndUpdateFromHandler(configUnitsPreUpdate, configUnitsArray, i);
// 
//                       }
// 
//                       // Delete fingerprint for deleted objects
//                       FlushMode deleteFlushMode = persistenceFactory.getSessionFactory()
//                                      .getCurrentSession().getFlushMode();
//                       persistenceFactory.getSessionFactory().getCurrentSession()
//                                      .setFlushMode(FlushMode.MANUAL);
//                try {
//                               for (int i = 0; i < configUnitsArray.length; ++i) {
//                                      for (ConfigUnit cu : configUnitsArray[i]) {
//                                              if ((save && deploy)
//                                                             && (cu.getOperation() == ConfigOperation.DELETE)) {
//                                                     String modelKey = getModelKey(cu.getIntendedModel());
//                                                     if ((modelKey.length() == 0) || !(skipDeleteSet.contains(modelKey))){        
//                                                             deleteFingerprint(deviceId, cu.getIntendedModel());
//                                                     }
//                                              }
//                                      }
//                               }
//                       } finally {
//                       // Reset flushing mode
//                        persistenceFactory.getSessionFactory().getCurrentSession().setFlushMode(deleteFlushMode);
//                       }
//               } catch (Exception e) {
//                       if (e instanceof XMPCheckedException) {
//                               throw (XMPCheckedException) e;
//                       } else {
//                               throw log.getXMPConfigCheckedException("CONFIG_003", e,
//                                              new Object[] {});
//                       }
//               } finally {
//                       /*
//                       * Reset connected model graph index and config operation. Used by
//                       * PreUpdateHandler/PreDeleteHandler to bail out in case of
//                       * invocations other than those caused by PreUpdate or PreDelete
//                       * trigger.
//                       */                   
//                       clearThreadLocals();
//               }
//              
//               // Execute postSave hooks, if any
//               if (configHooks != null) {
//                       for (IConfigHook configHook : configHooks) {
//                               try {
//                                      long beforeTs = System.currentTimeMillis();
//                                      configHook.postSave(new ConfigHookContext(deviceId,
//                                                     featureConfigSpec.getFeature(), configUnitsArray));
//                                      log.perf((System.currentTimeMillis() - beforeTs) / 1000.0
//                                                     + " seconds - postSave hook in "
//                                                     + configHook.getClass().getName());
//                               } catch (XMPConfigHookException e) {
//                                       log.error(ConfigServiceUtil.getStackTraceAsString(e));
//                                      throw log.getXMPConfigCheckedException("CONFIG_049", e,
//                                                     new Object[] {});
//                               }
//                       }
//               }
// 
//               return configUnitsArray;
// 
//        }
// 
//        /**
//        * Convert the decorators to ConfigUnits
//        *
//         * @param decorators
//        * @param visitedModels
//        * @param configUnits
//        * @param save
//        * @param rollback
//        * @throws XMPCheckedException
//        */
//        protected void convertDecoratorsToConfigUnits(String deviceId,
//                       ObjectCloner objectCloner, IntentObjectCloner intentObjectCloner,
//                       Object[] decorators, List<ConfigUnit> configUnits,
//                       Set<String> allowedToVisitModels,
//                       Map<String, InstanceImpl> keyToOriginalObject,
//                       boolean save,
//                       boolean rollback) throws XMPCheckedException {
//               if (decorators != null && decorators.length > 0) {
//                       for (Object decoratorObj : decorators) {
//                               InstanceImpl decoratorInstanceImpl = (InstanceImpl) decoratorObj;
//                               log.debug("decorator " + decoratorInstanceImpl);
//                               decomposeConnectedModelGraph(deviceId, objectCloner, intentObjectCloner,
//                                              allowedToVisitModels,
//                                              decoratorInstanceImpl, new HashSet<String>(),
//                                              configUnits, keyToOriginalObject, save, rollback, false);
//                       }
//               }
//        }
//       
//        /**
//        * Validate {@link BulkConfigSpecification} object. Validations performed
//        * are required parameter checks and JSR-303 constraint checks for model
//        * attributes.
//        *
//         * @param configSpec
//        *            {@link BulkConfigSpecification} object.
//        * @throws XMPCheckedException
//        */
//        @Override
//        public void validate(AbstractConfigSpecification bulkConfigSpec)
//                       throws XMPCheckedException {
//               // Validate required parameters
//               validateRequiredParameters((BulkConfigSpecification) bulkConfigSpec);
// 
//               // Validate model against JSR-303 (javax.validation) constraints
//               validateJSR303Constraints((BulkConfigSpecification) bulkConfigSpec);
//        }
// 
//        /**
//        * get commands specific to a feature. Also updates NOM with the generated
//        * commands
//        *
//         * @param deviceId
//        * @param featureCommands
//        * @param BulkCommandsTemplates
//        * @return
//        * @throws Exception
//        */
// 
// 
// 
// 
//        /**
//        * Validate {@link BulkConfigSpecification} passed-in by the caller for
//        * required parameters.
//        *
//         * @param configSpec
//        *            {@link ConfigSpecification} passed-in by the caller.
//        * @throws XMPCheckedException
//        */
//        private void validateRequiredParameters(
//                       BulkConfigSpecification bulkConfigSpec) throws XMPCheckedException {
//               try {
//                       // ConfigSpecification cannot be null
//                       if (bulkConfigSpec == null) {
//                               throw new IllegalArgumentException(
//                                              "BulkConfigSpecification is null.");
//                       }
// 
//                       // All fields in ConfigSpecification are required
//                       String deviceId = bulkConfigSpec.getDeviceId();
//                       if (StringUtils.isBlank(deviceId)) {
//                               throw new IllegalArgumentException(
//                                              "Device Id in BulkConfigSpecification is empty.");
//                       }
//                      
//                // Templates
//            BulkCommandsTemplate[] templateConfigSpecifications
//                = bulkConfigSpec.getBulkCommandsTemplates();
//            boolean isTemplates = null != templateConfigSpecifications
//                    && 0 != templateConfigSpecifications.length;
//            if (null == templateConfigSpecifications) {
//                bulkConfigSpec.setBulkCommandsTemplates(new BulkCommandsTemplate[0]);
//            }
//           
//            // Features
//            FeatureConfigSpecification[] featureConfigSpecifications = bulkConfigSpec
//                    .getFeatureConfigSpecifications();
//            boolean isFeatures = null != featureConfigSpecifications
//                    && 0 != featureConfigSpecifications.length;
//            if (null == featureConfigSpecifications) {
//                bulkConfigSpec.setFeatureConfigSpecifications(new FeatureConfigSpecification[0]);
//            }
//           
//            if (!isTemplates && !isFeatures) {
//                throw new IllegalArgumentException(
//                        "No feature or template specifications passed.");
//            }
// 
//            // Validating templates
//            if (isTemplates) {
//                for (BulkCommandsTemplate template : templateConfigSpecifications) {
//                    validateTemplet(template);
//                }
//            }
// 
//            // Validating features
//            if (isFeatures) {
//                for (FeatureConfigSpecification featureConfigSpecification : featureConfigSpecifications)
//                    validateFeature(featureConfigSpecification);
//            }
// 
//               } catch (IllegalArgumentException e) {
//                       throw log.getXMPConfigCheckedException("CONFIG_001",
//                                      new Object[] { e.getMessage() });
//               }
//        }
//       
//    /**
//     * Validate BulkCommandsTemplate
//     * */
//   private void validateTemplet(BulkCommandsTemplate template) throws IllegalArgumentException {
//       
//        // Due to FT tests template can be null:
//        // CreateConfigTests: testBulkCreateWithBulkCommandsTemplateHavingNullAppendCommandTemplate
//       // CreateConfigTests:testBulkCreateWithBulkCommandsTemplateHavingNullPrependCommandTemplateâ€‹
//        if (null == template) {
//            return;
//        }
// 
//        if (null == template.getBulkCommandsTemplateType()) {
//            throw new IllegalArgumentException("Template type can not be null.");
//        }
//    }
//   
//    /**
//     * Validate {@link FeatureConfigSpecification}.
//     * */
//    private void validateFeature(FeatureConfigSpecification featureConfigSpecification) {
//        
//        String feature = featureConfigSpecification.getFeature();
//        if (StringUtils.isBlank(feature)) {
//            throw new IllegalArgumentException(
//                    "Feature name in FeatureConfigSpecification is empty.");
//        }
//        ConnectedModelGraphSpecification[] connectedModelGraphSpecifications = featureConfigSpecification
//                .getConnectedModelGraphSpecifications();
//        if (connectedModelGraphSpecifications == null
//                || connectedModelGraphSpecifications.length == 0) {
//            throw new IllegalArgumentException(
//                    "No ConnectedModelGraphSpecifications found for feature "
//                            + feature);
//        }
//        for (ConnectedModelGraphSpecification connectedModelGraphSpecification : connectedModelGraphSpecifications) {
//            ConfigOperation operation = connectedModelGraphSpecification
//                    .getOperation();
//            if ((operation == null)
//                    || (operation == ConfigOperation.UNKNOWN)) {
//                throw new IllegalArgumentException(
//                        "Config operation in ConnectedModelGraphSpecification is invalid.");
//            }
//            Object cmg = connectedModelGraphSpecification
//                    .getConnectedModelGraph();
//            if (!(cmg instanceof InstanceImpl)) {
//                if (cmg == null) {
//                    throw new IllegalArgumentException("Model is null.");
//                } else {
//                    throw new IllegalArgumentException(
//                            "Model is invalid. It does not extend from "
//                                    + InstanceImpl.class.getName()
//                                    + " " + cmg);
//                }
//            } else {
//                // Log a warning if instanceVersion attribute exists
//                // (Optimistic locking should not be enabled in models)
//                boolean optimisticLockingEnabled = false;
//                try {
//                    cmg.getClass().getDeclaredField("instanceVersion");
//                    optimisticLockingEnabled = true;
//                } catch (Exception e) {
//                    // Do nothing!
//                } finally {
//                    if (optimisticLockingEnabled) {
//                        log.warning(cmg.getClass().getCanonicalName()
//                                + " has optimistic locking enabled. Edit tigerstripe.xml in the model project -> uncheck supportVersion in XMP Hibernate Mapping Generator Settings -> regenerate the model jar -> redeploy it in XMP platform.");
//                    }
//                }
//            }
// 
//            // *********check for valid decorator instance
//            Object[] decorators = connectedModelGraphSpecification
//                    .getDecorators();
//            if (decorators != null && decorators.length > 0) {
//                for (Object decorator : decorators) {
//                    if (!(decorator instanceof InstanceImpl)) {
//                        throw new IllegalArgumentException(
//                                "Model is invalid. It does not extend from "
//                                        + InstanceImpl.class.getName()
//                                        + " " + decorator);
//                    }
//                }
//            }
//        }
//    }
// 
//        /**
//        * Validate model against JSR-303 (javax.validation) constraints.
//        *
//         * @param configSpec
//        *            {@link BulkConfigSpecification} passed-in by the caller.
//        * @throws XMPCheckedException
//        */
//        private void validateJSR303Constraints(
//                       BulkConfigSpecification bulkConfigSpec) throws XMPCheckedException {
// 
//               for (FeatureConfigSpecification featureConfigSpecification : bulkConfigSpec
//                               .getFeatureConfigSpecifications()) {
//                       String feature = featureConfigSpecification.getFeature();
//                       InputStream deviceSpecificValidationFile = getDeviceSpecificValidationFile(
//                                      bulkConfigSpec.getDeviceId(), feature);
//                       for (ConnectedModelGraphSpecification connectedModelGraphSpecification : featureConfigSpecification
//                                      .getConnectedModelGraphSpecifications()) {
//                               Object connectedModelGraph = connectedModelGraphSpecification
//                                              .getConnectedModelGraph();
//                               Object[] connectedModelGraphAsArray = { connectedModelGraph };
//                               ConfigOperation operation = connectedModelGraphSpecification
//                                              .getOperation();
//                               // Validate model only for create and update operations
//                               if ((ConfigOperation.CREATE == operation)
//                                              || (ConfigOperation.UPDATE == operation)) {
//                                      // Validate model against JSR-303 (javax.validation)
//                                      // constraints
//                                      try {
//                                              configValidationService.validate(
//                                                             connectedModelGraphAsArray,
//                                                             deviceSpecificValidationFile);
//                                      } catch (XMPValidationException e) {
//                                              // Validation failed. Abort configuration operation and
//                                              // re-throw
//                                              // the exception for upper layers to handle
//                                              log.log("CONFIG_002", e.toString());
//                                              throw e;
//                                      }
//                               }
// 
//                       }
//               }
// 
//        }
// 
//        public void pushToDB(AsyncConfigDevice asyncConfigDevice, TransactionStatus status) throws XMPCheckedException {
//               String deviceId = asyncConfigDevice.getDeviceId();
//               BulkConfigService bulkConfigService = asyncConfigDevice.getAsyncConfigRequest().getBulkConfigService();
//               BulkConfigSpecification bulkConfigSpec = asyncConfigDevice.getBulkConfigSpec();
//               Set<String> skipDeleteSet = asyncConfigDevice.getSkipDeleteSet();
//               boolean shouldValidateForConfigurable = asyncConfigDevice.getAsyncConfigRequest().
//                                                                                                   getConfigServiceSettings().shouldValidateForConfigurable();
//               long transactionId = bulkConfigSpec.getTransactionId();
//               try{
//                       int featureConfigSpecIndex = 0;
//                       for (FeatureConfigSpecification featureConfigSpecification : bulkConfigSpec
//                                      .getFeatureConfigSpecifications()) {
//                               // update Inventory and collect config units.
//                               Map<String, InstanceImpl> keyToOriginalObject = new HashMap<String, InstanceImpl>();
//                               FeatureToAllowedToVisitModelList allowedToVisitModel = asyncConfigDevice.getFeatureToAllowedToVisitModelLists().get(featureConfigSpecIndex);
//                              
//                               List<IConfigHook> configHooks = bulkConfigService.getConfigHookManager().getConfigHooks(
//                                              asyncConfigDevice.getDeviceId(), featureConfigSpecification.getFeature(),
//                                              asyncConfigDevice.getAsyncConfigRequest().getConfigServiceSettings());
//                               List<ConfigUnit>[] configUnitsArray = bulkConfigService
//                                              .updateInventory(deviceId, transactionId,
//                                                             featureConfigSpecification, allowedToVisitModel.allowedToVisitModels, status, true, true,
//                                                             skipDeleteSet, keyToOriginalObject, allowedToVisitModel.excludeAssociations, shouldValidateForConfigurable,
//                                                             configHooks);
//                               featureConfigSpecIndex++;
//                              
//                               // Log
//                               ConfigLoggingUtil log = asyncConfigDevice.getAsyncConfigRequest().getBulkConfigService().getLog();
//                               for (int i = 0; i < configUnitsArray.length; ++i) {
//                                      // Added for debugging
//                                      log.debug("Connected model graph " + i
//                                                     + ", Number of objects in pushToDB model = "
//                                                     + configUnitsArray[i].size());
//                                      for (int k = 0; k < configUnitsArray[i].size(); ++k) {
//                                              ConfigUnit cu = configUnitsArray[i].get(k);
//                                              log.debug((k + 1)
//                                                             + ") "
//                                                             + cu.getOperation()
//                                                             + "/"
//                                                             + ((ConfigOperation.DELETE == cu.getOperation()) ? cu
//                                                                             .getCurrentModel() : cu.getIntendedModel()));
//                                      }
//                               }
//                              
//                               // Update fingerprint
//                              updateFingerprintSameTransaction(status,
//                                              bulkConfigSpec.getDeviceId(), featureConfigSpecification.getFeature(),
//                                              configUnitsArray, skipDeleteSet, true);
//                       }
//                       log.info("pushToDB Objects in all connected model graphs persisted successfully.");
//               } catch (Exception e) {
//                       log.log("CONFIG_003", e, new Object[] {});
//                       if (e instanceof XMPCheckedException) {
//                               throw (XMPCheckedException) e;
//                       } else {
//                               throw log.getXMPConfigCheckedException("CONFIG_003", e,
//                                              new Object[] {});
//                       }
//               }
//        }
//}
// 