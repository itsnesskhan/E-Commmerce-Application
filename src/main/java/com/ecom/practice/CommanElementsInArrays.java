package com.ecom.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CommanElementsInArrays {
	
	public static void main(String[] args) {
		String string = "0000000";

		if (string.matches("                         ")) {

			System.out.println("format varified");
		} else {
			System.out.println("not varifiled");
		}

	}

	ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) {
//       HashSet<Integer> set1= (HashSet<Integer>) Arrays.stream(A).boxed().collect(Collectors.toSet());
//       HashSet<Integer> set2 = (HashSet<Integer>) Arrays.stream(B).boxed().collect(Collectors.toSet());
//       HashSet<Integer> set3 = (HashSet<Integer>) Arrays.stream(C).boxed().collect(Collectors.toSet());
//      
//       
//       set1.retainAll(set2);

		ArrayList<Integer> list = new ArrayList<>();

		int x = 0, y = 0, z = 0;
		while (x < n1 && y < n2 && z < n3) {

			if (A[x] == B[y] && B[y] == C[z]) {
				list.add(A[x]);
			} else if (A[x] < B[y]) {
				x++;
			} else if (B[y] < C[z]) {
				y++;
			} else {
				z++;
			}
		}

		return list;
	}


}
