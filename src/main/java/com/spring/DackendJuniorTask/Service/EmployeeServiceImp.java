package com.spring.DackendJuniorTask.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.DackendJuniorTask.Entity.EmployeeEntity;
import com.spring.DackendJuniorTask.Model.EmployeeModel;
import com.spring.DackendJuniorTask.Repository.EmployeeRepository;
import com.spring.DackendJuniorTask.Transformer.EmployeeEntityToModelTransformer;
import com.spring.DackendJuniorTask.Transformer.EmployeeModelToEntityTransformer;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeEntityToModelTransformer entityToModelTransformer;
	
	@Autowired
	private EmployeeModelToEntityTransformer  modelToEntityTransformer;

	@Override
	public EmployeeModel saveEmployee(EmployeeModel employeeModel) {
		// TODO Auto-generated method stub
		return entityToModelTransformer.apply(
				employeeRepository.save(modelToEntityTransformer.apply(employeeModel)));
	}



	@Override
	public EmployeeModel getEmployeeById(Integer id) {
		Optional<EmployeeEntity> emOptional = employeeRepository.findById(id);
		if(emOptional.isPresent()) {
			return entityToModelTransformer.apply(emOptional.get());
		}
		return null;
	}

	@Override
	public EmployeeModel updateEmployee(EmployeeModel employeeModel) {
		if(employeeModel== null) 
			return null;
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeModel.getId());
		if(employeeEntity.isPresent()) {
			EmployeeEntity entity = employeeRepository.save(modelToEntityTransformer.apply(employeeModel));
			return entityToModelTransformer.apply(entity);
		}
		
		return null;
	}


	@Override
	public EmployeeModel getByEmail(String email) {
		Optional<EmployeeEntity> emOptional = employeeRepository.findByEmail(email);
		if(emOptional.isPresent()) {
			return entityToModelTransformer.apply(emOptional.get());
		}
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<EmployeeEntity> emOptional = employeeRepository.findById(id);
		if(emOptional.isPresent()) {
			 employeeRepository.delete(emOptional.get());
			 return true;
		}
		return false;
	}

	@Override
	public List<EmployeeModel> getAllEmployees() {
	Iterable<EmployeeEntity> employeeEntityDB = employeeRepository.findAll();
		
		if(employeeEntityDB == null) {
			return null;
		}
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
		for(EmployeeEntity employeeEntity : employeeEntityDB) {
			employeeList.add(entityToModelTransformer.apply(employeeEntity));
		}
	 
	  return employeeList;
	 	
		}



	@Override
	public EmployeeModel getAllEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	}
	


