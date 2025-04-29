package com.spring.DackendJuniorTask.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.DackendJuniorTask.Entity.EmployeeEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface EmployeeRepository extends CrudRepository<EmployeeEntity , Integer>{

	public Optional<EmployeeEntity> findById(Integer id);
	public Iterable<EmployeeEntity> findAll();
	public Optional<EmployeeEntity> findByEmail(String email);

}
