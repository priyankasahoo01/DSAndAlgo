package com.ds.sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class NewTest {

    static int getMaxLenSubArray(int[] inputArray) {
        if(inputArray == null || inputArray.length==0){
            return 0;
        }
//        int size =0, size1=0;
        int size=0;
        
        int sumP =inputArray[0];int lastI=-1;
        int n = inputArray.length;
        for(int i =0; i <n; i++){
            int currSum =0;
            for(int j =0; j<n ; j++){
                currSum= currSum+inputArray[j];
                if(currSum==0){
                    if(size>(j-i+1)){
                        size=maxLen;
                    }else{
                        size=(j-i+1);
                    }
                    
                }
            }
            return size;
                          
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        } else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int res;
        int inputArray_size = 0;
        inputArray_size = Integer.parseInt(in.nextLine().trim());

        int[] inputArray = new int[inputArray_size];
        for (int i = 0; i < inputArray_size; i++) {
            int inputArray_item;
            inputArray_item = Integer.parseInt(in.nextLine().trim());
            inputArray[i] = inputArray_item;
        }

        res = getMaxLenSubArray(inputArray);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}
