package com.spring.DackendJuniorTask.Model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeModel {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private Integer companyId;
	private List<SkillModel> skills;
	

}
