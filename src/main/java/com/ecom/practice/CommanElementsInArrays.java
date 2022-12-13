package com.ecom.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CommanElementsInArrays {
	
    static ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) 
    {
//        HashSet<Object> set = new HashSet<>();
//        for (int i = 0; i < A.length; i++) {
//			set.add(A[i]);
//		}
//        for (int i = 0; i < B.length; i++) {
//			set.retainAll(Arrays.stream(B).boxed().collect(Collectors.toList()));
//		}
//        for (int i = 0; i < C.length; i++) {
//			set.retainAll(Arrays.stream(C).boxed().collect(Collectors.toList()));
//		}
    	
    	return new ArrayList<>(null);
    }

	public static void main(String[] args) {
		int n1 = 6;
		int[] A = { 1, 5, 10, 20, 40, 80 };
		int n2 = 5;
		int[] B = { 6, 7, 20, 80, 100 };
		int n3 = 8;
		int C[] = { 3, 4, 15, 20, 30, 70, 80, 120 };
		System.out.println(commonElements(A, B, C, n1, n2, n3));
	}

}
