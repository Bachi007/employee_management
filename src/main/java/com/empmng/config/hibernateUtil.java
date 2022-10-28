package com.empmng.config;

import java.util.Properties;

import com.empmng.model.employee;
import com.empmng.model.project;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class hibernateUtil {
	
	// creating session factory object
		private static SessionFactory sesfac;

		// getting logger in hibernateUtil class
		static Logger log = Logger.getLogger(hibernateUtil.class);

		public static SessionFactory getSessionFactory() {

			// create a new session factory if not exist
			if (sesfac == null) {

				// setting properties of hibernate to connect with database
				try {
					Configuration config = new Configuration();
					Properties pro = new Properties();

					// setting hibernate properties to access database
					pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); // getting mysql driver
					pro.put(Environment.URL, "jdbc:mysql://localhost:3306/ProjectLive"); // connecting with database
					pro.put(Environment.USER, "root"); // user name of Mysql server
					pro.put(Environment.PASS, "Divya123"); // password of Mysql server
					pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect"); // setting mysql dialect
					pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread"); // checking the session by no of execution
																					// of threads
					pro.put(Environment.HBM2DDL_AUTO, "update"); // creating or updating the table if already exist
					pro.put(Environment.SHOW_SQL, "false"); // by indicating false we are not going to show sql process in
															// our console

					config.setProperties(pro); // configure properties that we created
					config.addAnnotatedClass(employee.class); // adding entity classes
					config.addAnnotatedClass(project.class);
					sesfac = config.buildSessionFactory();

				} catch (Exception e) { // if any exception occur handling the exception
					log.info(e.getMessage());
				}
			}
			return sesfac;
		}

		// getSession method to create a new session from sessionFactory pool
		// everytime this method is called
		public static Session getSession() {

			return getSessionFactory().openSession();
		}
}
