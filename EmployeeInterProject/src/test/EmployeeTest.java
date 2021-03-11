package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.DBManager;
import dao.EmployeeDAO;
import dto.EmployeeDTO;

class EmployeeTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DBManager.getInstance().getConnection().setAutoCommit(false);
	}

	@DisplayName("사원 정보 등록 테스트")
	@Test
	void insertTest() {
		try {
			int count = EmployeeDAO.getInstance().insertEmployee(new EmployeeDTO("SS99", "홍길동", 4, 5));
			if(count == 0)
				fail("사원등록 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("사원등록 실패");
		}
	}

	@DisplayName("사원 정보 검색 테스트")
	@Test
	void searchTest() {
		ArrayList<EmployeeDTO> list = EmployeeDAO.getInstance().selectEmployee("department","5");
		if(list.size() == 0)
			fail("해당하는 사원이 없습니다.");
	}
	
	@DisplayName("사원 정보 삭제 테스트")
	@Test
	void deleteTest() {
		int count = EmployeeDAO.getInstance().deleteEmployeeDao("SS99");
		if(count == 0)
			fail("사원삭제 실패");
	}


	@AfterAll
	public static void end() {
		try {
			DBManager.getInstance().getConnection().rollback();
			DBManager.getInstance().getConnection().close();
			System.out.println("롤백 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
