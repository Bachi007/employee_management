package com.empmng.daoimpl;

import com.empmng.config.hibernateUtil;
import com.empmng.dao.loginregister;
import com.empmng.exception.globalException;
import com.empmng.model.employee;

import org.hibernate.Session;

public class loginregisterImpl implements loginregister {

	// Method 1
	// To login into Dashboard
	// User can login using their User Name and Password
	// That present in database
	@Override
	public employee login(String userName, String password) throws globalException {
		// TODO Auto-generated method stub
		try (Session ses = hibernateUtil.getSession()) {
			ses.beginTransaction();
			// Checking if user exist in database with given user name
			employee empVar = (employee) ses.createQuery("from employee where empUserName =: username")
					.setParameter("username", userName).uniqueResult();
			// if user present this block will execute
			if (empVar != null) {
				// checking fetching password from database
				// matching with given password
				if (empVar.getEmpPassword().equals(password))
					return empVar;// if password matches return employee
				else // if password does not match throws an exception
					throw new globalException("Wrong Password !!! \nTry Again.");
			} else // id user not exist in database with given username throws an exception
				throw new globalException("User not found!!!! \nRegister Yourself or Try with different Username");
		}
	}

	// Method 2
	// to register a new user
	// an employee object pass in method to store in database
	@Override
	public int register(employee emp) throws globalException {
		// TODO Auto-generated method stub
		// Starting a new session
		try (Session ses = hibernateUtil.getSession()) {
			String name = emp.getEmpName();// fetching username of given object
			// checking if an user with same username is already exist in database
			employee empVar = (employee) ses.createQuery("from employee where empUserName =: username")
					.setParameter("username", name).uniqueResult();
			// if user not exist with same username
			// we can add this employee in database as unique user
			if (empVar == null) {
				ses.beginTransaction();// starting new transaction
				ses.save(emp); // adding employee in database
				ses.getTransaction().commit(); // commiting the changes in database
				return 1;
			} else // if user already exist with same username discard the registration
					// throw an exception to try again with different username to register
				throw new globalException("User Name is already taken!!! \nTry again with new user name.");
		}
	}

}