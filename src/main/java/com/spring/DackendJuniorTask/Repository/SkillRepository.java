package com.spring.DackendJuniorTask.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.spring.DackendJuniorTask.Entity.SkillEntity;
import com.spring.DackendJuniorTask.Model.SkillModel;

import jakarta.transaction.Transactional;

@Transactional
public interface SkillRepository  extends CrudRepository<SkillEntity, Integer>{

	public Optional<SkillEntity> findById(Integer id);
	//public Iterable<SkillEntity> findAll();
	public Optional<SkillEntity> findByName(String name);
}
