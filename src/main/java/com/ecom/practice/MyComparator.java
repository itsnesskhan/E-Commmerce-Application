package com.ecom.practice;

import java.util.Comparator;

public class MyComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
			int name = o1.getName().compareTo(o2.getName());
			
			int salary = o1.getSalary().compareTo(o2.getSalary());
			
			int department = o1.getDepartment().compareTo(o2.getDepartment());
			
			if (name == 0 ) {
				return (salary==0)?department:salary;
			}
			else {
				return name;
			}
	}

	

}
