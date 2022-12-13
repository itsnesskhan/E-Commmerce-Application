package com.ecom.practice;


public class SwapIntgers {
	
		
	public static void swapSrings(int[] arr) {
		
		arr[0] = arr[0] ^ arr[1];
		arr[1] = arr[0] ^ arr[1];
		arr[0] = arr[0] ^ arr[1];
		
		
		
	}
	
	public static void main(String[] args) {
		  Integer str2 = 6;
		 Integer str1 = 7;
		 int arr[] = {6,7};

		System.out.println("befor swap "+arr[0]+" ,"+arr[1]);
		
		swapSrings(arr);
		System.out.println("after swap "+arr[0]+" "+arr[1]);
		
	
	}

}
