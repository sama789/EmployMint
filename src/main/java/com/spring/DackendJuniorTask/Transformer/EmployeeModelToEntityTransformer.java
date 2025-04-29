package com.spring.DackendJuniorTask.Transformer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.DackendJuniorTask.Entity.CompanyEntity;
import com.spring.DackendJuniorTask.Entity.EmployeeEntity;
import com.spring.DackendJuniorTask.Entity.SkillEntity;
import com.spring.DackendJuniorTask.Model.EmployeeModel;
import com.spring.DackendJuniorTask.Model.SkillModel;
import com.spring.DackendJuniorTask.Repository.CompanyRepository;

@Component
public class EmployeeModelToEntityTransformer implements Function<EmployeeModel, EmployeeEntity>{
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private SkillModelToEntityTransformer skillModelToEntityTransformer;
	
	
	public EmployeeModelToEntityTransformer(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}


	@Override
	public EmployeeEntity apply(EmployeeModel employeeModel) {
		if(employeeModel == null) {
			return null;
		}
		if(employeeModel.getCompanyId() !=null) {
			Optional<CompanyEntity> companyEntity = companyRepository.findById(employeeModel.getCompanyId());
			if(companyEntity.isPresent()) {
				EmployeeEntity employeeEntity =  EmployeeEntity.builder()
						.id(employeeModel.getId())
						.firstName(employeeModel.getFirstName())
						.lastName(employeeModel.getLastName())
						.email(employeeModel.getEmail())
						.companyEntity(companyEntity.get())
						.build();
				employeeEntity.setSkills(getSkillEntityListFromModelList(employeeModel.getSkills(), employeeEntity));
				return employeeEntity;
			}
		}
		EmployeeEntity employeeEntity = EmployeeEntity.builder()
				.id(employeeModel.getId())
				.firstName(employeeModel.getFirstName())
				.lastName(employeeModel.getLastName())
				.email(employeeModel.getEmail())
				.companyEntity(null)
				.build();
		employeeEntity.setSkills(getSkillEntityListFromModelList(employeeModel.getSkills(), employeeEntity));
		return null;
	}


	private List<SkillEntity> getSkillEntityListFromModelList(List<SkillModel> skillModelList, EmployeeEntity employeeEntity) {
		if(skillModelList == null || skillModelList.size() <= 0) {
			return null;
		}
		List<SkillEntity> returnListSkill = new ArrayList<>();
		for(SkillModel skillmodel : skillModelList) {
			SkillEntity skillEntity = skillModelToEntityTransformer.apply(skillmodel);
			skillEntity.addEmpolyee(employeeEntity);
			returnListSkill.add(skillEntity);
		}
			return returnListSkill;
		
	}

}
