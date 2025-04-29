package com.spring.DackendJuniorTask.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.DackendJuniorTask.Entity.EmployeeEntity;
import com.spring.DackendJuniorTask.Entity.SkillEntity;
import com.spring.DackendJuniorTask.Model.SkillModel;
import com.spring.DackendJuniorTask.Repository.EmployeeRepository;

@Component
public class SkillModelToEntityTransformer  implements Function< SkillModel, SkillEntity>{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public SkillEntity apply(SkillModel skill) {
		if(skill == null)
			return null;
		else
			return SkillEntity.builder()
					.id(skill.getId())
					.name(skill.getName())
					.employees(getEmplyeeEntityListFromIds(skill.getEmployees()))
					.build();
	}
	private List<EmployeeEntity> getEmplyeeEntityListFromIds(List<Integer> employees) {
		if(employees == null || employees.size() <= 0) {
			return null;
		}
		List<EmployeeEntity> returnList = new ArrayList<>();
		for(Integer employee : employees) {
			Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employee);
			if(employeeEntity.isPresent()) {
				returnList.add(employeeEntity.get());
			}
		}
		return returnList;
	}

}
