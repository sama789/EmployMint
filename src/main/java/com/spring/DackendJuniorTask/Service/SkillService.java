package com.spring.DackendJuniorTask.Service;

import java.util.List;

import com.spring.DackendJuniorTask.Model.SkillModel;

public interface SkillService {
	public SkillModel saveSkill(SkillModel skill);
	public SkillModel getSkillById(Integer id);
	public SkillModel updateSkill(SkillModel skillModel);
	public SkillModel getSkillByName(String name);
	
	public Boolean deleteById(Integer id);
	public List<SkillModel> getAllSkills();

}
