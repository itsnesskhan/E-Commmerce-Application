package com.ecom.practice;

import java.util.ArrayList;
import java.util.Arrays;

public class CommonEelements {
	
    public  static String isSubset( long a1[], long a2[], long n, long m) {
    	Arrays.sort(a1);
        for(int i=0;i<m;i++){
           if( Arrays.binarySearch(a1, a2[i])== -1) {
        	   return "NO";
           }
            
        }
        return "YES";
    }
	
	static ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) 
    {
//       HashSet<Integer> set1= (HashSet<Integer>) Arrays.stream(A).boxed().collect(Collectors.toSet());
//       HashSet<Integer> set2 = (HashSet<Integer>) Arrays.stream(B).boxed().collect(Collectors.toSet());
//       HashSet<Integer> set3 = (HashSet<Integer>) Arrays.stream(C).boxed().collect(Collectors.toSet());
//      
//       
//       set1.retainAll(set2);

		ArrayList<Integer> list = new ArrayList<>();
		int x = 0,y=0,z=0;
		while (x<n1 && y<n2 && z<n3) {
			
			if (A[x]== B[y] && B[y] == C[z]) {
				list.add(A[x]);
				x++;
				y++;
				z++;
			}
			else if (A[x]<B[y]) {
				x++;
			}
			else if (B[y]<C[z]) {
				y++;
			}
			else {
				z++;
			}
		}
       
       return list;
    }
	
	
	

	public static void main(String[] args) {
		int n1 = 6; int[] A = {1, 5, 10, 20, 40, 80};
		int n2 = 5; int[] B = {6, 7, 20, 80, 100};
		int n3 = 8; int[] C = {3, 4, 15, 20, 30, 70, 80, 120};
		ArrayList<Integer> commonElements = commonElements(A, B, C, n1, n2, n3);
		System.out.println(commonElements);
		long a1[] = {11, 1, 13, 21, 3, 7};
		long a2[] = {11, 3, 7, 1};
		String subset = isSubset(a1, a2, a1.length, a2.length);
		System.out.println(subset);
	}
//	Output: 20 80

}
