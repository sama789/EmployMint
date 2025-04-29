package com.spring.DackendJuniorTask.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "skill")

public class SkillEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy= "skills")
	private List<EmployeeEntity> employees;
	
	
	public void addEmpolyee(EmployeeEntity employeeEntity) {
		if(employees == null) {
			employees = new ArrayList<>();
			employees.add(employeeEntity);
			employeeEntity.addSkill(this);
		}
	}
	

}
