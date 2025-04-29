package com.spring.DackendJuniorTask.Utils;

import java.util.ArrayList;
import java.util.List;

import com.spring.DackendJuniorTask.Entity.EmployeeEntity;
import com.spring.DackendJuniorTask.Model.EmployeeModel;
import com.spring.DackendJuniorTask.Transformer.EmployeeEntityToModelTransformer;

public class ListTransformUitls {
	
	public static List<EmployeeModel> getListEmployeeFromEntityToModel(List<EmployeeEntity> employeeList){
		EmployeeEntityToModelTransformer employyeEntityToModel = new EmployeeEntityToModelTransformer();
		
		if(employeeList == null || employeeList.size() <= 0) {
			return null;
		}
		List<EmployeeModel> returnList = new ArrayList<>();
		for(EmployeeEntity employeeEntity : employeeList) {
			returnList.add(employyeEntityToModel.apply(employeeEntity));
		}
		return returnList;
	}

}
