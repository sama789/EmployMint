package com.spring.DackendJuniorTask.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.DackendJuniorTask.Entity.SkillEntity;
import com.spring.DackendJuniorTask.Model.SkillModel;
import com.spring.DackendJuniorTask.Repository.SkillRepository;
import com.spring.DackendJuniorTask.Transformer.SkillEntityToModelTransformer;
import com.spring.DackendJuniorTask.Transformer.SkillModelToEntityTransformer;

@Service
public class SkillServiceImpl implements SkillService{
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired 
	private SkillEntityToModelTransformer entityToModelTransformer;
	
	@Autowired
	private SkillModelToEntityTransformer modelToEntityTransformer;

	@Override
	public SkillModel saveSkill(SkillModel skill) {
		return entityToModelTransformer.apply(
				skillRepository.save(modelToEntityTransformer.apply(skill)));
	}

	@Override
	public SkillModel getSkillById(Integer id) {
		java.util.Optional<SkillEntity> skillEntity = skillRepository.findById(id);
		if(skillEntity.isPresent()) {
			return entityToModelTransformer.apply(skillEntity.get());
		}
		return null;
	}

	@Override
	public SkillModel updateSkill(SkillModel skillModel) {
		if(skillModel == null) {
			return null;
		}
		java.util.Optional<SkillEntity> skillEntity = skillRepository.findById(skillModel.getId());
		if(skillEntity.isPresent()) {
			SkillEntity entityObj = skillRepository.save(modelToEntityTransformer.apply(skillModel));
			return entityToModelTransformer.apply(entityObj);
		}
		return null;
	}

	@Override
	public SkillModel getSkillByName(String name) {
		java.util.Optional<SkillEntity> skillEntity = skillRepository.findByName(name);
		if(skillEntity.isPresent()) {
			return entityToModelTransformer.apply(skillEntity.get());
		}
		return null;
	}

	@Override
	public Boolean deleteById(Integer id) {
		java.util.Optional<SkillEntity> skillEntity = skillRepository.findById(id);
		if(skillEntity.isPresent()) {
			skillRepository.delete(skillEntity.get());
			return true;
		}
		return false;
	}

	@Override
	public List<SkillModel> getAllSkills() {
		Iterable<SkillEntity> skillDB = skillRepository.findAll();
		if(skillDB == null) {
			return null; 
		}
		List<SkillModel> skillList = new ArrayList<SkillModel>();
		for(SkillEntity skillEntity : skillDB) {
			skillList.add(entityToModelTransformer.apply(skillEntity));
		}
		return skillList;
	}

}
