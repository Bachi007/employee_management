package com.empmng.dao;

import java.util.List;

import com.empmng.exception.globalException;
import com.empmng.model.employee;
import com.empmng.model.project;



public interface hrdashboard {
	
	public List<employee> allEmployee();
	public List<project> allProjects();
	public employee viewEmployeeProfile(int empId) throws globalException ;
	public int deltEmp(int empId) throws globalException ;
	public int createProject(project p) throws globalException;
	public int allotProject(int empId,int pId) throws globalException;
	
	
	

}
