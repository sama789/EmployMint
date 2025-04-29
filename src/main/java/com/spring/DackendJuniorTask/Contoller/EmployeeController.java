package com.spring.DackendJuniorTask.Contoller;

import java.util.List;
import java.util.Optional;

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
import com.spring.DackendJuniorTask.Model.EmployeeModel;
import com.spring.DackendJuniorTask.Repository.EmployeeRepository;
import com.spring.DackendJuniorTask.Service.EmployeeService;
import com.spring.DackendJuniorTask.Transformer.EmployeeEntityToModelTransformer;
import com.spring.DackendJuniorTask.Transformer.EmployeeModelToEntityTransformer;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService enEmployeeService;
	
	@Autowired
	private EmployeeEntityToModelTransformer entityToModelTransformer;
	
	
	@Autowired
	private EmployeeModelToEntityTransformer modelToEntityTransformer;
	
	@GetMapping("/getPingWithStatus")
	public ResponseEntity<String> getPingWithStatus(){
		
		return new ResponseEntity<>("Ready to go Status, hier is EmployeeController", HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/create")
	public EmployeeModel saveEmployee(@RequestBody(required = false) EmployeeModel employeeModel) {
		return enEmployeeService.saveEmployee(employeeModel);
	}
	
	@GetMapping("/getid")
	public EmployeeModel getById(@RequestParam(required = false)Integer id) {
		return enEmployeeService.getEmployeeById(id);
	}
	
	
	@GetMapping("/getemail")
	public EmployeeModel getByEmail(@RequestParam(required = false)String email) {
		return enEmployeeService.getByEmail(email);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEmpolyee(@RequestParam(required = false) Integer id){
		if(enEmployeeService.deleteById(id)) {
			return new ResponseEntity<String>("Employee has been deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<EmployeeModel> updateEmployee(@RequestBody(required = false) EmployeeModel model){
		EmployeeModel employeeModel = enEmployeeService.updateEmployee(model);
		if(employeeModel == null) {
			return new ResponseEntity("cant be updated", HttpStatus.EXPECTATION_FAILED);
		}else {
			return new ResponseEntity<EmployeeModel>(employeeModel, HttpStatus.OK);
		}
	}


	@GetMapping("/all")
	public ResponseEntity<List<EmployeeModel>> getAll(){
		
		List<EmployeeModel>  listEmployee = enEmployeeService.getAllEmployees();
		if( listEmployee != null ) {
			return new ResponseEntity<List<EmployeeModel>>(listEmployee, HttpStatus.OK);
		}
		else 
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);

	}
}
