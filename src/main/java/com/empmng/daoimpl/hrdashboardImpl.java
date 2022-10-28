package com.empmng.daoimpl;

//importing required packages
import java.util.List;

import javax.persistence.Query;

import com.empmng.config.hibernateUtil;
import com.empmng.dao.hrdashboard;
import com.empmng.exception.globalException;
import com.empmng.model.employee;
import com.empmng.model.project;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class hrdashboardImpl implements hrdashboard {
	
//method 1 -> to print the list of all the employees present in the database
	static Logger log=Logger.getLogger(hrdashboardImpl.class);
	@Override
	public List<employee> allEmployee() {
		// TODO Auto-generated method stub
		//Autoclosable session
				try(Session ses=hibernateUtil.getSession()){
					//getting rows of a employee table
					String employee="employee";
					Query qu=ses.createQuery("from employee where empDesignation=:employee").setParameter("employee", employee);
					List<employee> employeeList=qu.getResultList();
					return employeeList;
				}
	}
	
//method 2 -> to print the list of all the projects in the database
	@Override
	public List<project> allProjects() {
		// TODO Auto-generated method stub
		//autoclosable session object
				try(Session ses=hibernateUtil.getSession()){
					//getting rows of a project table
					Query qu=ses.createQuery("from project");
					List<project> projectList=qu.getResultList();
					return projectList;
				}

	}
	
//method 3-> to view individual employee profile 
	@Override
	public employee viewEmployeeProfile(int empId)   {
		// TODO Auto-generated method stub
		//Autoclosable session
        try(Session ses=hibernateUtil.getSession()){
			
			employee e1=ses.get(employee.class, empId);
			return e1;
		}
		
	}
	
//method 4-> to delete the employee from the database
	@Override
	public int deltEmp(int empId){
		// TODO Auto-generated method stub
		//Autoclosable session
				try(Session ses=hibernateUtil.getSession())
				{
					ses.beginTransaction();
					int status= ses.createQuery("delete from employee where empId=:empId").setParameter("empId", empId).executeUpdate();
					ses.getTransaction().commit();
					return status;
				}		
	}
	
//method 5 -> to create a project in the database
	@Override
	public int createProject(project p) {
		// TODO Auto-generated method stub
		//Autoclosable session
				try(Session ses=hibernateUtil.getSession()){
					
					ses.beginTransaction();
					String projectName= p.getProjectName();
					project p2=null;
					//checking for existing projectName
					p2=(project)ses.createQuery("from project where projectName=:projectName").setParameter("projectName", projectName).uniqueResult();
					//if projectName is unique then we can save the room
					if(p2==null)
					{
						ses.save(p);
						//commit
						ses.getTransaction().commit();
						
					}
					
					else {
						throw new globalException(" Project  is already existed..!");
					}
					
				}
				
				catch (globalException e) {
					log.info(e.getMessage());
				}
				return 1;
	}
	
//method 6 -> to allot a project to any employee 
	@Override
	public int allotProject(int empId, int pId) {
		// TODO Auto-generated method stub
		//Autoclosable session
				try(Session ses=hibernateUtil.getSession())
				{
					ses.beginTransaction();
					int status=ses.createQuery("update employee set empProject_projectId=:rId where empId=:empId").setParameter("pId", pId).setParameter("empId", empId).executeUpdate();
					ses.getTransaction().commit();
					return status;
					
				}
	}
	

	
}