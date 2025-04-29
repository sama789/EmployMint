package com.spring.DackendJuniorTask.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "employee")
public class EmployeeEntity {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String email;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name="fk_company", referencedColumnName ="id", nullable= true)
	private CompanyEntity companyEntity;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name ="employee_skill", joinColumns = @JoinColumn(name="employee_id"), inverseJoinColumns = @JoinColumn(name="skill_id"))
	private List<SkillEntity> skills;

	public void addSkill(SkillEntity skillEntity) {
		if(skills == null){
			skills = new ArrayList<>();
			skills.add(skillEntity);
		}
	}
	
	public void removeSkill(SkillEntity skillEntity) {
		skills.remove( skillEntity);
	}
	

}
