package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.DBManager;
import dao.ProductDAO;
import dto.ProductDTO;

class ProductDTOTest {
	private static Connection conn = null;
	
	@BeforeAll
	static void setUp() throws Exception {
		File file = new File("product.json");
		FileReader fis = new FileReader(file);
		BufferedReader br = new BufferedReader(fis);
		String result = "";
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			result += str;
		}
		JSONArray jsonArray = new JSONArray(result);
		conn = DBManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		String sql = "insert into product values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		for(int i=0; i<jsonArray.length(); i++) {
			pstmt = conn.prepareStatement(sql);
			JSONObject obj = new JSONObject(jsonArray.get(i).toString());
			pstmt.setString(1, obj.getString("product_no"));
			pstmt.setString(2, obj.getString("product_name"));
			pstmt.setInt(3, obj.getInt("price"));
			pstmt.setInt(4, obj.getInt("ea"));
			pstmt.setString(5, obj.getString("maker"));
			pstmt.executeUpdate();
			pstmt.close();
		}
		
	}

	@DisplayName("제품정보 검색 테스트")
	@Test
	void testSelectProduct() {
		ProductDTO dto = ProductDAO.getInstance().selectProduct("89451111152");
		if(dto == null)
			fail("오류, 해당 데이터는 있는데 검색결과가 없습니다. SQL문을 확인해보세요.");
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
