package com.spring.DackendJuniorTask.Service;

import java.util.List;

import com.spring.DackendJuniorTask.Model.CompanyModel;

public interface CompanyService {
	public CompanyModel saveCompany(CompanyModel companyModel);
	public CompanyModel getCompanyById(Integer id );
	public CompanyModel updateCompany(CompanyModel companyModel);
	
	public Boolean validateUser(String email, String password);
	public CompanyModel getCompanyByEmail(String email );
	public boolean deleteById(int id);
	
	public List<CompanyModel> getAllCompanies();

}
