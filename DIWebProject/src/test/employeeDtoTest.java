package test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import dto.EmployeeDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import di.DiContainer;

class employeeDtoTest {

	private static AnnotationConfigApplicationContext context;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		context = DiContainer.getContext();
	}

	@DisplayName("DI를 적용한   셋팅")
	@Test
	void testEmployeeDto() {
		String eno = "AA11";
		String name = "홍길동";
		String department = "영업";
		String position = "1";
		
		for(int i=0; i<1000;i++) {
			EmployeeDto dto = context.getBean("employeeDto",EmployeeDto.class);
			dto.init(eno, name, department, position, 0);
			System.out.println(dto.toString());
		}
	}
	
	@DisplayName("DI를 적용하지 않은 셋팅")
	@Test
	void testEmployeeDtoStringSTringStringInt() {
		String eno = "AA11";
		String name = "홍길동";
		String department = "영업";
		String position = "1";
		
		for(int i=0; i<1000;i++) {
			EmployeeDto dto = new EmployeeDto(eno, name, department, position, 0);
			System.out.println(dto.toString());
		}
	}
	

}
