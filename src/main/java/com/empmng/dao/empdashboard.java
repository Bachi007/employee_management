package com.empmng.dao;

import java.util.List;

import com.empmng.exception.globalException;
import com.empmng.model.employee;
import com.empmng.model.project;

public interface empdashboard {
public List<employee> allEmployee();
public List<project> allProjects();
public employee viewEmployeeProfile(int empId);
public project viewSpecificProject(int pId) ;
public int empPhnNumber(int empId,String number);
public int empDesignation(int empId);
public int empLocation(int empId);
public int deltEmp(int empId);
public int createProject(project p);
public int allotProject(int empId, int pId);
public int salaryHike(int empId);

}