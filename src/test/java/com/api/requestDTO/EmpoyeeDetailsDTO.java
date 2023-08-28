package com.api.requestDTO;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Comparator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EmpoyeeDetailsDTO {

	private int employeeID;
	private String employeeName;
	private String employeeSalary;
    
	public EmpoyeeDetailsDTO() {};
	
	public EmpoyeeDetailsDTO(int employeeID, String employeeName, String employeeSalary) {
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

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	@Override
	public String toString() {
		return "EmpoyeeDetailsDTO {employeeID=" + employeeID + ", employeeName=" + employeeName + ", employeeSalary="
				+ employeeSalary + "}";
	}



}
