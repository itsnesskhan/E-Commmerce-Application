package com.ecom.practice;

import java.util.ArrayList;

import net.bytebuddy.description.field.FieldDescription.InGenericShape;

public class MaxSubbArray {

	
   static ArrayList<Integer> findSubarray(int a[], int n) {

    	Integer current = a[0];
    	Integer globalmax= a[0];
    	Integer globalStart=0;
    	int start=0;
    	int end=0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<n;i++) {
        	
        	if (a[i]> a[i]+current) {
				current = a[i];
				start = i;
			}
        	else if (a[i]<a[i]+current) {
				current+=a[i];
			}
        	if (current>globalmax) {
				globalmax = current;
				end = i;
				globalStart = start;
			}
        }
        for (int i = globalStart; i <=end; i++) {
			list.add(a[i]);
		}
    	return list;
    }
    
    public static void main(String[] args) {
//		int arr[] = {1,2,5,-7,2,3};
    	int arr[] = {-4 ,-7, -6, 0, -19, 2, -7, -18, -17, 7, -9, -1, 8, 19, -16, -5, 3 ,8 ,5 ,11, 14, 17, -7, 11,};
		ArrayList<Integer> findSubarray = findSubarray(arr, arr.length);
		System.out.println(findSubarray);
	}
}
