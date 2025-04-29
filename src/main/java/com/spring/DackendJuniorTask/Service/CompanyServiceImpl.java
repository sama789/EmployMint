package com.spring.DackendJuniorTask.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.DackendJuniorTask.Entity.CompanyEntity;
import com.spring.DackendJuniorTask.Model.CompanyModel;
import com.spring.DackendJuniorTask.Repository.CompanyRepository;

import com.spring.DackendJuniorTask.Transformer.CompanyEntityToModel;
import com.spring.DackendJuniorTask.Transformer.CompanyModelToEntityTransformer;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	
	@Autowired
	private CompanyEntityToModel companyEntityToModel;
	
	@Autowired
	private CompanyModelToEntityTransformer companyModelToEntityTransformer;
	

	@Override
	public CompanyModel saveCompany(CompanyModel companyModel) {
		return companyEntityToModel.apply(
				companyRepository.save(companyModelToEntityTransformer.apply(companyModel)));
	}



	@Override
	public CompanyModel getCompanyById(Integer id) {
		Optional<CompanyEntity> compnayEntity = companyRepository.findById(id);
		if(compnayEntity.isPresent()) {
			return companyEntityToModel.apply(compnayEntity.get());
		}
		return null;
	}

	@Override
	public CompanyModel updateCompany(CompanyModel companyModel) {
		if(companyModel.getId() == null) {
			return null;
		}
		Optional<CompanyEntity> companyEntityOptional = companyRepository.findById(companyModel.getId());
		if(companyEntityOptional.isPresent()) {
			CompanyEntity companyEntity = companyEntityOptional.get();
			companyEntity.setEmail(companyModel.getEmail());
			companyEntity .setPassword(companyModel.getPassword());
			companyEntity = companyRepository.save(companyEntity );
			return companyEntityToModel.apply(companyEntity);
		}
		
		return null;
	}

	@Override
	public Boolean validateUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyModel getCompanyByEmail(String email) {
		Optional<CompanyEntity> companyDB = companyRepository.findByEmail(email);
		if(companyDB.isPresent()) {
			return companyEntityToModel.apply(companyDB.get());
		}
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<CompanyEntity> companyDB = companyRepository.findById(id);
		if(companyDB.isPresent()) {
			companyRepository.delete(companyDB.get());
			return true;

		}
		return false;
	}



	@Override
	public List<CompanyModel> getAllCompanies() {
	Iterable<CompanyEntity> companyDB =companyRepository.findAll();
			
			if(companyDB == null) {
				return null;
			}
			List<CompanyModel> companyList = new ArrayList<CompanyModel>();
			for(CompanyEntity companyEntity : companyDB) {
				companyList.add(companyEntityToModel.apply(companyEntity));
			}
		 
		  return companyList;
		 	
			}



}
