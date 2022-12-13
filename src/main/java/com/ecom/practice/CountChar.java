package com.ecom.practice;

import java.util.HashMap;

public class CountChar {
	
	public static HashMap<Character, Integer> countOcurrence(String string){
		
		char[] array = string.toCharArray();
		HashMap<Character, Integer> hashMap = new HashMap<>();
		for (char c : array) {
			if (hashMap.containsKey(c)) {
				hashMap.put(c, hashMap.get(c)+1);
			}else {
				hashMap.put(c, 1);
			}
		}
		return hashMap;
	}
	
	public static void main(String[] args) {
		String string = "NASSER KHAN";
		HashMap<Character,Integer> countOcurrence = countOcurrence(string);
		System.out.println(countOcurrence);
		
	}

}
