package com.empmng.daoimpl;

import java.util.List;

import javax.persistence.Query;

import com.empmng.config.hibernateUtil;
import com.empmng.dao.empdashboard;
import com.empmng.exception.globalException;
import com.empmng.model.employee;
import com.empmng.model.project;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public  class empdashboardImpl implements empdashboard{
	// method 1 : fetches all data of employee table
	static Logger log=Logger.getLogger(empdashboardImpl.class);
	@Override
	public List<employee> allEmployee(){
		//making Session
		try(Session ses = hibernateUtil.getSession()){
			//Query to fetch employee table's data
			Query q = ses.createQuery("from employee");
			List<employee> eList = q .getResultList();
			return eList;
			}
	}
	//method 2: fetches all data of project table
	@Override
	public List<project> allProjects() {
	//making Session
		try(Session ses = hibernateUtil.getSession()){
			//Query to fetch project table's data
			Query q = ses.createQuery("from project");
			List<project> pList = q .getResultList();
			return pList;
			}
	}
	// method 3 : fetches data of a specific employee from employee table
	@Override
	public employee viewEmployeeProfile(int empId){
		employee emp = null;
		try (Session ses = hibernateUtil.getSession()){
			// get() methods gets all the value from table of specific empId
			 emp = ses.get( employee.class,empId);
			if (emp == null) {
				// throw exception if Employee with entered empId do not exists  
				throw new globalException("Employee do not exists");
			}
		} catch (globalException e) {
			log.info(e.getMessage());
		}
		return emp;
		
	}
	//method 4: fetches data of Specific project
	@Override
	public project viewSpecificProject(int pId){
		project p = null;
		try(Session ses = hibernateUtil.getSession()){
			// get() methods gets all the value from table of specific pId
			p = ses.get(project.class, pId);
			if (p==null) {
				throw new globalException("Project with this Id do not exists");
			}
		} catch (globalException e) {
			log.info(e.getMessage());
		}
		return p;
		
	}
	//method 5: change Employee's Phone Number
	@Override
	public int empPhnNumber(int empId,String number) {
		int update=0;
		try(Session ses = hibernateUtil.getSession()){
			employee emp = ses.get( employee.class,empId);
			if (emp == null) {
				// throw exception if Employee with entered empId do not exists  
				throw new globalException("Employee do not exists");
			}
			else {
				//if employee with empId exist then update phone number
				//HQL query to update PhoneNumber
				update = ses.createQuery("update employee set empPhone =: number where empId =:empId").setParameter("number", number).setParameter("empId", empId).executeUpdate();
				ses.getTransaction().commit();
				
			}
		} catch (globalException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return update;
	}
	@Override
	public int empDesignation(int empId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int empLocation(int empId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deltEmp(int empId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createProject(project p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int allotProject(int empId, int pId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int salaryHike(int empId) {
		
		return 0;
	}

	
	
}