package com.ecom.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.zaxxer.hikari.HikariJNDIFactory;

import ch.qos.logback.core.joran.conditional.IfAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
class Employee implements Comparable<Employee> {
	
	private Integer eid;
	private String name;
	private Double salary;
	private String department;
	
	@Override
	public int compareTo(Employee o) {
		return this.eid - o.eid;
	}

}


public class ComparatorEx{
	
	public static void printList(List<?> list) {
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("natural order");
		ArrayList<Employee> list = new ArrayList<>();
		list.add(new Employee(101, "Nasser Khan", 15000d, "IT"));
		list.add(new Employee(104, "Mohit Kumrawat", 30000d, "CIVIL"));
		list.add(new Employee(105, "Javed Khan", 20000d, "IT"));
		list.add(new Employee(105, "Javed Khan", 20000d, "CIVIL"));
		list.add(new Employee(103, "Vishal Sain", 25000d, "IT"));
		list.add(new Employee(102, "Akshay Jain", 270000d, "IT"));	
		
		printList(list);
		
		Collections.sort(list);
		
		System.out.println("after sorting");
		
		printList(list);
		
		System.out.println("sorting based on salary");
		
		
		Collections.sort(list, (a,b)-> a.getSalary()<b.getSalary()?-1:a.getSalary()>b.getSalary()?1:0);
		
		printList(list);
		
		System.out.println("sorting based on multiple fileds");
		
		Collections.sort(list, new MyComparator());
		printList(list);
		
		
		
		Map<String, List<Employee>> map = list.stream().collect(Collectors.groupingBy(emp-> emp.getDepartment()));
		Set<String> set = map.keySet();
		for (String string : set) {
			System.out.println(string+" "+ map.get(string).size());
		}
		
		System.out.println("sorting based on last name");
		list.sort(new LastNameSortComparator());
		printList(list);
		String reverseWords = reverseWords("i.am.ness");
		System.out.println(reverseWords);
		
		
		
		
	
		
	}
	
	static String reverseWords(String S) 
    { 
		String rev="";
        String[] arr =  S.split("[.]");
  
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
			String seprator= i!=arr.length?".":"";
			rev=arr[i]+seprator+rev;
		}
        return rev;
    } 
	
	
}
