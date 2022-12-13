package com.ecom.practice;

import java.util.Comparator;

public class LastNameSortComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		 return o1.getName().split("\\s")[1].compareTo(o2.getName().split("\\s")[1]);
	}

}
