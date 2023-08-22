package com.api.requestDTO;

import java.util.Comparator;

public class EmpoyeeDetailsDTO implements Comparator<EmpoyeeDetailsDTO>{

	private int employeeID;
	private String employeeName;
	private int employeeSalary;
    
	public EmpoyeeDetailsDTO() {};
	
	public EmpoyeeDetailsDTO(int employeeID, String employeeName, int employeeSalary) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public int getEmployeeSalary() {
		return employeeSalary;
	}

	@Override
	public String toString() {
		return "EmpoyeeDetailsDTO {employeeID=" + employeeID + ", employeeName=" + employeeName + ", employeeSalary="
				+ employeeSalary + "}";
	}

	@Override
	public int compare(EmpoyeeDetailsDTO o1, EmpoyeeDetailsDTO o2) {
		return o1.getEmployeeName().compareTo(o2.getEmployeeName());
	}

}
