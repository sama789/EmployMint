package com.spring.DackendJuniorTask.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.DackendJuniorTask.Entity.CompanyEntity;
import com.spring.DackendJuniorTask.Entity.EmployeeEntity;
import com.spring.DackendJuniorTask.Model.CompanyModel;
import com.spring.DackendJuniorTask.Model.EmployeeModel;

@Component
public class CompanyModelToEntityTransformer implements Function<CompanyModel, CompanyEntity> {

	@Autowired
	private EmployeeModelToEntityTransformer modelToEntityTransformer;
	@Override
	public CompanyEntity apply(CompanyModel companyModel) {
		if(companyModel==null) {
			return null;
		}
		return CompanyEntity.builder()
				.id(companyModel.getId())
				.email(companyModel.getEmail())
				.password(companyModel.getPassword())
				.employees(getListEmployee(companyModel.getEmployees()))
				.build();
	}

	
	private List<EmployeeEntity> getListEmployee(List<EmployeeModel> employeeList){
		if(employeeList == null || employeeList.size() <= 0) {
			return null;
		}
		List<EmployeeEntity> empEntities = new ArrayList<>();
		for(EmployeeModel employeeModel : employeeList) {
			empEntities.add(modelToEntityTransformer.apply(employeeModel));
		}
		return empEntities;
	}
}
