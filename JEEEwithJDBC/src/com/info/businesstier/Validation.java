package com.info.businesstier;


public class Validation {

	public void validate(EmployeeTO userTO) throws Exception {

		String errorMessage = null;

		if (!nameValidation(userTO.getName())) {
			errorMessage = "user name is not valid";
		} else if (!empIdValidation(userTO.getEmpId())) {
			errorMessage = "Employee id is not valid "; 
		}else if (!salaryValidation(userTO.getSalary())) {
			errorMessage = "invalid balance";
//			apconfig.
		}
		
		
		if (errorMessage != null) {
			throw new Exception(errorMessage);
		}

	}

	private boolean empIdValidation(String empId) {
		boolean isValid = true;
		
		empId = empId.trim();
		
		if(empId.length()>100 || empId.length() == 0){
			isValid = false;
		}else{
			
			for(int i = 0; i<empId.length();i++){
				if(empId.charAt(i)==' ' ||empId.charAt(i)=='.'||empId.charAt(i)=='-'){
					isValid = false;
					break;
				}					
			}			
		}		
		return isValid;
	}

	private boolean nameValidation(String name) {	
		boolean isValid = true;

		name = name.trim();
		
		if(name.equals("")){
			isValid = false;
		}

		return isValid;
	}


	private boolean salaryValidation(double balance) {
		boolean isValid = true;
		if (balance < 10000 || balance > 100000){
			isValid = false;
		}
		return isValid;
	}
}
