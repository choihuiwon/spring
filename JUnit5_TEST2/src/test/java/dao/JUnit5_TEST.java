package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.EmployeeDto;

class JUnit5_TEST {

	@BeforeAll
	static void setUp() {
		System.out.println("테스트 맨 처음에 한번 수행할 일");
	}
	
	@BeforeEach
	void init() {
		System.out.println("테스트 작업전 수행");
	}

	@DisplayName("사원정보리스트 조회 테스트")
	@Test
	void testSelectPositionArea() {
		String str = EmployeeDao.getInstance().selectPositionArea(0);
		if(str.length() == 2)
			fail("데이터가 없습니다."); 	// 작업 실패시 나타낼 행동
		else
			System.out.println(str);
	}

	@DisplayName("하위연봉5명 조회 테스트")
	@Test
	void testGetLowSalary() {
		String str = EmployeeDao.getInstance().getLowSalary();
		if(str.length() < 3)
			fail("데이터가 적습니다.");
		else
			System.out.println(str);
	}

	@DisplayName("사원정보 하나 조회")
	@Test
	void testSelectEmployee() {
//		EmployeeDto dto = EmployeeDao.getInstance().selectEmployee("TQ98");
//		assertSame(dto, dto);			// == 연산 (객체에서는 쓸수없는 연산임 주소가 같은 경우)
		EmployeeDto dto = new EmployeeDto("TQ98", "강병헌", "영업", "과장", 14619);
		assertEquals(dto, EmployeeDao.getInstance().selectEmployee("TQ98"));
	}
	
	@DisplayName("빈 리스트 확인 테스트")
	@Test
	void testSelectEmployeeAllList() {
		assertTrue(!EmployeeDao.getInstance().selectEmployeeAllList().isEmpty());	// 비어있으면 false 비어있지 않으면 true로 나오게끔
	}
	
	@AfterEach	// 각 테스트 메서드가 실행이 끝난 후 실행
	void done() {
		System.out.println("테스트 작업 후 실행");
	}
	
	@AfterAll
	static void end() {
		System.out.println("테스트 맨 마지막에 한번 수행할 일");
	}
}
