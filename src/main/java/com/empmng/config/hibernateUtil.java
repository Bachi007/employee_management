package com.empmng.config;

import java.util.Properties;

import com.empmng.App;
import com.empmng.model.employee;
import com.empmng.model.project;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class hibernateUtil {
	//logger object
	static Logger log = Logger.getLogger(App.class);
	// SessionFactory object
	private static SessionFactory sesfac;
	public static SessionFactory getSessionFactory() {
		// SessionFactory object has null value then add cofiguration
		if (sesfac==null) {
			try {
				Configuration config = new Configuration();
				Properties pro = new Properties();
				pro.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
				pro.put(Environment.URL, "jdbc:mysql://localhost:3306/ProjectLive");
				pro.put(Environment.USER,"root" );
				pro.put(Environment.PASS, "17Rup@01");
				pro.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
				pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				pro.put(Environment.HBM2DDL_AUTO,"update");
				pro.put(Environment.SHOW_SQL,"false");
				
				config.setProperties(pro);
				config.addAnnotatedClass(employee.class);
				config.addAnnotatedClass(project.class);
				sesfac=config.buildSessionFactory();
				
		}
			// if any exception occurs
		catch(Exception e) {
			log.info(e.getMessage());
		}
		}
		return sesfac;
	}
	// Session method
	public static Session getSession() {
		
		return getSessionFactory().openSession();
	}
}