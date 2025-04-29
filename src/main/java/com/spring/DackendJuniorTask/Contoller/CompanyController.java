package com.spring.DackendJuniorTask.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.DackendJuniorTask.Model.CompanyModel;
import com.spring.DackendJuniorTask.Service.CompanyService;
@RestController
@RequestMapping("/company")
public class CompanyController{

	@Autowired
	CompanyService companyService;
	
	@GetMapping("/getPingWithStatus")
	public ResponseEntity<String> getPingWithStatus(){
		
		return new ResponseEntity<>("Ready to go Status, hier is CompanyController", HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CompanyModel>> getAll(){
		
		List<CompanyModel>  listCompany = companyService.getAllCompanies();
		if( listCompany != null ) {
			return new ResponseEntity<List<CompanyModel>>(listCompany, HttpStatus.OK);
		}
		else 
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);

	}
	@PostMapping("/create")
	public CompanyModel save(@RequestBody(required = false) CompanyModel companyModel){
		return companyService.saveCompany(companyModel);
	}
	
	
	@GetMapping("/getid")
	public CompanyModel getCompanyById(@RequestParam(required = false) Integer id) {
		return companyService.getCompanyById(id);
	}
	
	@GetMapping("/getemail")
	public CompanyModel getCompanyByEmail(@RequestParam(required = false) String email) {
		return companyService.getCompanyByEmail(email);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCompany(@RequestParam(value ="id") Integer id){
		if(companyService.deleteById(id)) {
			return new ResponseEntity<String>("Company with "+ id+" has been deleted ", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<CompanyModel> updateCompany(@RequestBody(required =  false) CompanyModel model){
		CompanyModel companyModel = companyService.updateCompany(model);
		if(companyModel == null) {
			return new ResponseEntity("cant be updated", HttpStatus.EXPECTATION_FAILED);
		}else {
			return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
		}
	}
}
