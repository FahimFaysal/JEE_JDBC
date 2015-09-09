package com.infy.presentationtier;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.info.businesstier.EmployeeTO;
import com.info.businesstier.Maneger;

public class Tester {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	EmployeeTO employeeTO = new EmployeeTO();
	Maneger manager = new Maneger();

	@Test	
	public void invalidUserName() throws Exception {
		employeeTO.setName("");
		employeeTO.setEmpId("emp21");
		employeeTO.setSalary(10000.00);
		
		thrown.expectMessage("user name is not valid");
		manager.addEmplyoee(employeeTO);
	}
	
	@Test
	public void valaidUpdate() throws Exception  {
		employeeTO.setEmpId("emp02"); // should found in list/DB

		employeeTO.setName("Senior Raghavendra");
		employeeTO.setSalary(70000);
		
		String actual = manager.updateEmployee(employeeTO);
		String expected = "Success update Employee with id "+employeeTO.getEmpId();
		
		assertEquals(expected, actual);
		
	}
	
	@Test //(expected = java.lang.Exception.class)
	public void invalaidUpdate() throws Exception  {
		employeeTO.setEmpId("01");
		employeeTO.setName("Senior Raghavendra");
		employeeTO.setSalary(70000);

		thrown.expectMessage("The Employee is not found !");
		manager.updateEmployee(employeeTO);				
	}

}
