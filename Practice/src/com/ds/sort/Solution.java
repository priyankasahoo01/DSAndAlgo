package com.ds.sort;
import java.util.Scanner;

public class Solution {

    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        int[] aux = getAuxArr(expenditure, 0, d);
        for (int i = d; i < expenditure.length; i++) {
            if (isNotify(expenditure, aux, i, d)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[] expenditure = new int[n];
        for(int expenditure_i = 0; expenditure_i < n; expenditure_i++){
            expenditure[expenditure_i] = in.nextInt();
        }
        
        int result = activityNotifications(expenditure, d);
        System.out.println(result);
        in.close();
    }
    
    private static boolean isNotify(int[] ip , int[] aux, int day, int d){
        int end = day, start = day - d;
        aux = getAux(ip,aux, start, end);
        
        int median = 0;
        median = getMedians(aux, d);
        if (ip[day] >=  median) {
            return true;
        }
        return false;
    }

    private static int getMedians(int[] aux, int d) {
        boolean isEven = d % 2 == 0;
        
        int data = 0;
        int cum = 0, prevCum=0;
        for (int i = 0; i < aux.length; i++) {
            
            cum+=aux[i];
            int mid=d/2;
            if(cum>(mid)){
                if(!isEven){
                    return 2*i;
                }else if(isEven && prevCum==mid){
                    return (i+data);
                }else{
                    return 2*i;
                }
            }
            prevCum=cum;
            data = i;
            

        }
        return -1;
    }

    private static int[] getAux(int[] ip, int[] aux, int start, int end) {
        if(start !=0){
            int startData = ip[start-1];
            if(aux[startData]>0){
                aux[startData]=aux[startData]-1;
            }
            
            int endData = ip[end-1];
            aux[endData]=aux[endData]+1;
        }
        return aux;
    }


    private static int[] getAuxArr(int[] ip, int start, int end) {
        int aux[] = new int[201];
        for (int i = start; i < end; i++) {
            int data = ip[i];
            aux[data] = aux[data] + 1;
        }
        return aux;
    }

}
