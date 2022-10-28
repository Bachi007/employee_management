package com.empmng.dao;

import java.util.List;

import com.empmng.exception.globalException;
import com.empmng.model.employee;
import com.empmng.model.project;



public interface hrdashboard {
	
	public List<employee> allEmployee();
	public List<project> allProjects();
	public employee viewEmployeeProfile(int empId)  ;
	public int deltEmp(int empId) ;
	public int createProject(project p);
	public int allotProject(int empId,int pId);
	
	
	

}