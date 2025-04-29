package com.spring.DackendJuniorTask.Service;
import java.util.List;

import com.spring.DackendJuniorTask.Model.EmployeeModel;

public interface EmployeeService {
	
	public EmployeeModel saveEmployee(EmployeeModel employeeModel);
	public EmployeeModel getAllEmployeeById(Integer id);
	
	public EmployeeModel getEmployeeById(Integer id );
	public EmployeeModel updateEmployee(EmployeeModel employeeModel);
	
	public EmployeeModel getByEmail(String email );
	public boolean deleteById(int id);
	public List<EmployeeModel> getAllEmployees();

}
