package com.ecom.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class FirstAndLastOccuranc {
	static int binarySearch(long arr[], int low, long high, long x) {
		if (high >= low) {
		
			int mid = (int) ((low + high) / 2);

			if ((mid == 0 || x > arr[mid - 1]) && (arr[mid] == x))
				return mid;
			else if (x > arr[mid])
				return binarySearch(arr, (mid + 1), high, x);
			else
				return binarySearch(arr, low, (mid - 1), x);
		}
		return -1;
	}

	public String isSubset(long a1[], long a2[], long n, long m) {
		Arrays.sort(a1);
		for (int i = 0; i < m; i++) {
			if (binarySearch(a1, 0, n-1, a2[i]) == -1) {
				return "NO";
			}
			
		}
		return "YES";
	}

	public static void frequencyCount(int arr[], int N, int P) {

//		HashMap<Integer, Integer> set = new HashMap<Integer,Integer>();
//        for(int i=0;i<N;i++){
//            if (set.containsKey(arr[i])) {
//				set.put(arr[i], set.get(arr[i])+1);
//			}
//            else {
//            	set.put(arr[i], 1);				
//			}
//        }
//        
//        System.out.println(set);
//        
//        for(int j=1;j<=P;j++){
//            if(set.containsKey(j)){
//                arr[j-1] = set.get(j);
//            }
//            else {
//				arr[j-1] = 0;
//			}
//        }
        
       List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
       for (int i = 1; i <= P; i++) {
		arr[i-1] = Collections.frequency(collect, i);
	}
       System.out.println(Arrays.toString(arr));
	}

	public long first(long arr[], int n, int x) {
		int low = 0;
		int high = n - 1;
		long res = -1;
		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[mid] > x) {
				high = mid - 1;
			} else if (arr[mid] < x) {
				low = mid + 1;
			} else {
				res = mid;
				high = mid - 1;
			}
		}
		return res;
	}

	public long last(long arr[], int n, int x) {
		int low = 0;
		int high = n - 1;
		long res = -1;
		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[mid] > x) {
				high = mid - 1;
			} else if (arr[mid] < x) {
				low = mid + 1;
			} else {
				res = mid;
				low = mid + 1;
			}
		}
		return res;
	}

	ArrayList<Long> find(long arr[], int n, int x) {
		ArrayList<Long> list = new ArrayList<>();
		long first = first(arr, n - 1, x);
		long last = last(arr, n - 1, x);
		list.add(first);
		list.add(last);
		return list;
	}

	public static void main(String[] args) {
		FirstAndLastOccuranc firstAndLastOccuranc = new FirstAndLastOccuranc();
//		long arr[] = { 1, 3, 5, 5, 5, 5, 7, 123, 125 };
		long arr[] = { 6, 9 };
		System.out.println(1 / 2);
		ArrayList<Long> find = firstAndLastOccuranc.find(arr, arr.length - 1, 9);
		System.out.println(find);
//		int[] arr2 = { 2, 3, 2, 3, 5 };
int arr2[]	 = {	3 ,2, 2, 2, 1};

		frequencyCount(arr2, arr2.length, 5);

	}
}
