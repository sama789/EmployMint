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

import com.spring.DackendJuniorTask.Entity.SkillEntity;
import com.spring.DackendJuniorTask.Model.CompanyModel;
import com.spring.DackendJuniorTask.Model.EmployeeModel;
import com.spring.DackendJuniorTask.Model.SkillModel;
import com.spring.DackendJuniorTask.Repository.SkillRepository;
import com.spring.DackendJuniorTask.Service.CompanyService;
import com.spring.DackendJuniorTask.Service.SkillService;
import com.spring.DackendJuniorTask.Transformer.SkillEntityToModelTransformer;
import com.spring.DackendJuniorTask.Transformer.SkillModelToEntityTransformer;

@RestController
@RequestMapping("/skill")
public class SkillController {
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private SkillEntityToModelTransformer entityToModelTransformer;
	
	@Autowired 
	private SkillModelToEntityTransformer modelToEntityTransformer;
	
	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping("/getPingWithStatus")
	public ResponseEntity<String> getPingWithStatus(){
		
		return new ResponseEntity<>("Ready to go Status, hier is SkillController", HttpStatus.ACCEPTED);
		
	}
	 
	@PostMapping("/create")
	public SkillModel saveSkill(@RequestBody(required = false) SkillModel skillModel) {
		return skillService.saveSkill(skillModel);
				}
	
	@GetMapping("/skills")
	public ResponseEntity<List<SkillModel>> getAll(){
		List<SkillModel> listSkill =  skillService.getAllSkills();
		if(listSkill !=null) {
			return new ResponseEntity<List<SkillModel>>(listSkill,  HttpStatus.OK);
		}else 
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
	}	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteSkill(@RequestParam(value="id") int id){
		if(skillService.deleteById(id)) {
			return new ResponseEntity<>("Skill with Id "+ id + "  has been deleted",  HttpStatus.OK);
		}else {
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping("/skillid")
	public SkillModel getSkill(@RequestParam(required = false) Integer id) {
		return skillService.getSkillById(id);
		
	}


	@PutMapping("/update")
	public ResponseEntity<SkillModel> edit(@RequestBody SkillModel skill){
		SkillModel skillModel = skillService.updateSkill(skill);

		if(skillModel !=null){
			return new ResponseEntity<SkillModel>(skillModel, HttpStatus.OK);
		}
		else 
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
		
	}




	
}
