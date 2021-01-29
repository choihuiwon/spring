package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.DBManager;
import dao.EmployeeDao;
import dto.EmployeeDto;

class EmployeeTest {
	private static Connection conn = null;
	@BeforeAll
	static void setUp() throws Exception {
		// json 파일 읽기
		File file = new File("employee.json");
		FileReader fis = new FileReader(file);
		BufferedReader br = new BufferedReader(fis);
		String result = "";
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			result += str;
		}
		System.out.println(result);
		// json 처리
		JSONArray jsonArray = new JSONArray(result);
		// db연결
		conn = DBManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		String sql = "insert into employee values(?,?,?,?)";
		PreparedStatement pstmt = null;
		
		for(int i=0; i<jsonArray.length(); i++) {
			pstmt = conn.prepareStatement(sql);
			JSONObject obj = new JSONObject(jsonArray.get(i).toString());
			pstmt.setString(1, obj.getString("eno"));
			pstmt.setString(1, obj.getString("name"));
			pstmt.setString(1, obj.getString("department"));
			pstmt.setString(1, Integer.toString(obj.getInt("position")));
			pstmt.executeUpdate();
			pstmt.close();
		}
		
	}

	@DisplayName("해당 사원 정보 삭제")
	@Test
	public void testDeleteEmployee() {
		String eno = "MR62";
		String sql = "delete from employee where eno like ?";
		conn = DBManager.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eno);
			int count = pstmt.executeUpdate();
			assertEquals(count, 1);
//			if(count == 0)
//				fail("사원정보 삭제 테스트 실패");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testSelectEmployeeAllList() {
		ArrayList<EmployeeDto> list = EmployeeDao.getInstance().selectEmployeeAllList();
		if(list.size() == 0)
			fail("조회할 데이터가 없습니다.");
		else {
			for(EmployeeDto l :list)
				System.out.println(l.toString());
		}
	}
	
	@AfterAll
	public static void end() {
		try {
			conn.rollback();
			conn.close();
			System.out.println("롤백 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
