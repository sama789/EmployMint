package com.spring.DackendJuniorTask.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.spring.DackendJuniorTask.Entity.EmployeeEntity;
import com.spring.DackendJuniorTask.Entity.SkillEntity;

import com.spring.DackendJuniorTask.Model.SkillModel;
@Component
public class SkillEntityToModelTransformer implements Function<SkillEntity, SkillModel>{

	@Override
	public SkillModel apply(SkillEntity skill) {
		if(skill == null)
			return null;
		return SkillModel.builder()
				.id(skill.getId())
				.name(skill.getName())
				.employees(getIntegerEmployeeFromEnityList(skill.getEmployees()))
				.build();
	}

	private List<Integer> getIntegerEmployeeFromEnityList(List<EmployeeEntity> employees) {
		if(employees == null || employees.size() <= 0) {
			return null;
		}
		List<Integer> returnList = new ArrayList<>();
		for(EmployeeEntity employeeEntity : employees) {
			returnList.add(employeeEntity.getId());
		}
		return returnList;
	}

}
