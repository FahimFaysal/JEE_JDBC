package com.infy.presentationtier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.info.businesstier.EmployeeTO;
import com.info.businesstier.Maneger;

public class InfyEmployee {

	public static void main(String[] args) {
		
		EmployeeTO employeeTO = new EmployeeTO();
		Maneger manager = new Maneger();
		
//		new InfyEmployee().addEmployee(employeeTO,manager);
//		new InfyEmployee().updateEmployee(employeeTO,manager);
//		new InfyEmployee().removeEmployee(manager);										
		
		new InfyEmployee().allEmployee(manager);	
	}	



	private void addEmployee(EmployeeTO employeeTO, Maneger manager) {
		
		employeeTO.setName("employe");
		employeeTO.setEmpId("emp011");
		employeeTO.setSalary(10000.00);
		employeeTO.setPhone("01736674652");
		employeeTO.setEmail("emp@infy.com");
						
		try {			
			manager.addEmplyoee(employeeTO);
			System.out.println("The Employee successfully add with \nEmployee ID: "+employeeTO.getEmpId()+"\nEmployee name: "+employeeTO.getName()+"\nEmployee Salary: "+employeeTO.getSalary()+"\nphone: "+employeeTO.getPhone()+"\nemail: "+employeeTO.getEmail());
		
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Error: "+e.getMessage());
		}
	}
	
	
	private void updateEmployee(EmployeeTO employeeTO, Maneger manager) {
		
		employeeTO.setEmpId("emp1000"); // should found in list/DB
		
		employeeTO.setName("Senior Raghavendra");
		employeeTO.setSalary(70000);
		
		try {
			System.out.println(manager.updateEmployee(employeeTO));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Error : "+e.getMessage());
		}
		
	}

	private void removeEmployee(Maneger manager) {
		
		String emId = "emp011";
		
		try {
			System.out.println(manager.deleteEmplyoee(emId));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	
	private void allEmployee(Maneger manager) {
		ArrayList<EmployeeTO> list;
		try {
			list = manager.allEmployee();
			Iterator iterator = list.iterator();
			System.out.println("=================All infy Employee==================");
			
			while(iterator.hasNext()) {				
				EmployeeTO employee = (EmployeeTO)iterator.next();
			    System.out.println("Id: "+employee.getEmpId()+"\tName: "+employee.getName()+" \t \t Salary: "+employee.getSalary()+" \t phone: "+employee.getPhone()+"\t Email: "+employee.getEmail());			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}										
	}

}
