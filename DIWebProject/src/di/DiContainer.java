package di;

import dto.EmployeeDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import config.DBManager;
import dao.EmployeeDao;

@Configuration
public class DiContainer {
	private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiContainer.class);
	
	public static AnnotationConfigApplicationContext getContext() {
		return context;
	}
	
	@Bean
	public DBManager manager() {
		return new DBManager();
	}
	
	@Bean
	public EmployeeDao employeeDao() {
		return new EmployeeDao(manager());
	}
	
	@Bean
	public EmployeeDto employeeDto() {
		return new EmployeeDto();
	}
}
