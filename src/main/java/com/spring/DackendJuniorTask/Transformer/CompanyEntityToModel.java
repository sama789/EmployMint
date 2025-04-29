package com.spring.DackendJuniorTask.Transformer;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.spring.DackendJuniorTask.Entity.CompanyEntity;
import com.spring.DackendJuniorTask.Model.CompanyModel;
import com.spring.DackendJuniorTask.Utils.ListTransformUitls;
@Component

public class CompanyEntityToModel implements Function<CompanyEntity, CompanyModel>{

	@Override
	public CompanyModel apply(CompanyEntity companyEntity) {
		if(companyEntity == null) {
			return null;
		}
		return CompanyModel.builder()
				.id(companyEntity.getId())
				.email(companyEntity.getEmail())
				.password(companyEntity.getPassword())
				.employees(ListTransformUitls.getListEmployeeFromEntityToModel(companyEntity.getEmployees()))
				.build();
		
	}

}
