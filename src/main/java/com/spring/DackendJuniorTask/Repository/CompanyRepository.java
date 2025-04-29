package com.spring.DackendJuniorTask.Repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.spring.DackendJuniorTask.Entity.CompanyEntity;
import jakarta.transaction.Transactional;

@Transactional
public interface CompanyRepository extends CrudRepository<CompanyEntity, Integer>{
	public Optional<CompanyEntity> findById(Integer id);
	public Optional<CompanyEntity> findByEmail(String eimal);
	public Iterable<CompanyEntity> findAll();
}
