package com.empmng.dao;

import com.empmng.exception.globalException;
import com.empmng.model.employee;

public interface loginregister {
	employee login(String userName, String password) throws globalException;

	int register(employee emp) throws globalException;
}