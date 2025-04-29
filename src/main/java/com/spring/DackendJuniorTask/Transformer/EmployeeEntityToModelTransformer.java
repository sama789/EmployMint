package com.spring.DackendJuniorTask.Transformer;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.spring.DackendJuniorTask.Entity.EmployeeEntity;
import com.spring.DackendJuniorTask.Model.EmployeeModel;


@Component
public class EmployeeEntityToModelTransformer implements Function<EmployeeEntity , EmployeeModel>{

	@Override
	public EmployeeModel apply(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		return EmployeeModel.builder()
				.id(employee.getId())
				.firstName(employee.getFirstName())
				.lastName(employee.getLastName())
				.email(employee.getEmail())
				.companyId(employee.getCompanyEntity().getId())
				.build();
	}

}
