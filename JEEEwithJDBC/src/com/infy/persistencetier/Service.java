package com.infy.persistencetier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.info.businesstier.EmployeeTO;

public class Service {
	Connection dbConnection = null;
	private ArrayList<EmployeeTO> employeeList = null;

	public EmployeeTO getEmployee(String empId) throws SQLException {
		EmployeeTO findEmployeeTO = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba", "faysal");

			PreparedStatement ps = dbConnection.prepareStatement("SELECT * FROM employee WHERE empid = ?");
			ps.setString(1, empId);

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				findEmployeeTO = new EmployeeTO();
				findEmployeeTO.setEmpId(resultSet.getString(1));
				findEmployeeTO.setName(resultSet.getString(2));
				findEmployeeTO.setSalary(resultSet.getDouble(3));
				findEmployeeTO.setPhone(resultSet.getString(4));
				findEmployeeTO.setEmail(resultSet.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.close();
		}
		return findEmployeeTO;
	}

	public void addEmployee(EmployeeTO employeeTO) throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba", "faysal");

			PreparedStatement ps = dbConnection.prepareStatement("INSERT INTO employee VALUES(?, ?, ?, ?, ?)");

			ps.setString(1, employeeTO.getEmpId());
			ps.setString(2, employeeTO.getName());
			ps.setDouble(3, employeeTO.getSalary());
			ps.setString(4, employeeTO.getPhone());
			ps.setString(5, employeeTO.getEmail());

			ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.close();
		}

	}

	public String deleteEmployee(EmployeeTO delteEmployeeTO) throws SQLException {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba", "faysal");

			PreparedStatement ps = dbConnection.prepareStatement("DELETE FROM employee WHERE empid = ?");
			ps.setString(1, delteEmployeeTO.getEmpId());
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.close();
		}
		return "The Employee successfully delete";
	}

	public String UpdateEmployee(EmployeeTO updateEmployeeTO) throws SQLException {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba", "faysal");

			PreparedStatement ps = dbConnection.prepareStatement("UPDATE employee SET name = ?, salary = ? WHERE empid = ?");
			ps.setString(1, updateEmployeeTO.getName());
			ps.setDouble(2, updateEmployeeTO.getSalary());
			ps.setString(3, updateEmployeeTO.getEmpId());

			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.close();
		}

		return "Success update Employee with id " + updateEmployeeTO.getEmpId();
	}

	public ArrayList<EmployeeTO> allEmployee() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba", "faysal");
			Statement statement = dbConnection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

			employeeList = new ArrayList<EmployeeTO>();
			EmployeeTO employeeTO;
			while (resultSet.next()) {
				employeeTO = new EmployeeTO();
				employeeTO.setEmpId(resultSet.getString(1));
				employeeTO.setName(resultSet.getString(2));
				employeeTO.setSalary(resultSet.getDouble(3));
				employeeTO.setPhone(resultSet.getString(4));
				employeeTO.setEmail(resultSet.getString(5));

				employeeList.add(employeeTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.close();
		}

		return employeeList;
	}

}
