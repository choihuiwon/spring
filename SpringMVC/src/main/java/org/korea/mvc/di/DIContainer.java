package org.korea.mvc.di;
import org.korea.mvc.dao.EmployeeDao;
import org.korea.mvc.dao.MemberDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import config.DBManager;

@Configuration
public class DIContainer {
	
	@Bean
	public DBManager manager() {
		return new DBManager();
	}
	
	@Bean
	public MemberDAO memberDAO() {
		return new MemberDAO(manager());
	}
	
	@Bean
	public EmployeeDao employeeDAO() {
		return new EmployeeDao(manager());
	}
}
