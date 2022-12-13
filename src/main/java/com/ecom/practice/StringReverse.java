package com.ecom.practice;

public class StringReverse {
	
	public static String reverseString(String str) {
		String rev = "";
		char[] arr = str.toCharArray();
		
		for (int i = 0; i < arr.length; i++) {
			rev= arr[i]+rev;
		}
		 return rev;
	}

	
	public static void main(String[] args) {
		String string = "Nasser Khan";
		String reverseString = reverseString(string);
		System.out.println(reverseString);
		
	}
}
