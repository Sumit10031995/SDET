package com.api.requestDTO;

import java.util.List;

public class Testtttt {
private List<EmpoyeeDetailsDTO> li;

public List<EmpoyeeDetailsDTO> getLi() {
	return li;
}

public void setLi(List<EmpoyeeDetailsDTO> li) {
	this.li = li;
}

public static class EmpoyeeDetailsDTO{
	private int employeeID;
	private String employeeName;
	private String employeeSalary;
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	@Override
	public String toString() {
		return "EmpoyeeDetailsDTO [employeeID=" + employeeID + ", employeeName=" + employeeName + ", employeeSalary="
				+ employeeSalary + "]";
	}
	
}

@Override
public String toString() {
	return "Test [li=" + li + "]";
}

}
