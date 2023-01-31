package com.ecom.practice;

public class SwapStrings {

	static String str1 = "NASSER";
	static String str2 = "KHAN";

	public static void main(String[] args) {
		str1 += str2; //NASSERKHAN
		
		str2 = str1.substring(0, str1.length() - str2.length()); 
		str1 = str1.substring(str2.length());
		
		System.out.println(str1);
		System.out.println(str2);
	

	}

}
