package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;
import dto.ProductDTO;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();
	private DBManager manager;
	private ProductDAO() {
		manager = DBManager.getInstance();
	}
	public static ProductDAO getInstance() {
		if(instance == null)
			instance = new ProductDAO();
		return instance;
	}
	
	// 상품 등록
	public void insertProduct(ProductDTO dto) throws SQLException {
		String sql = "insert into product values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		pstmt = manager.getConnection().prepareStatement(sql);
		pstmt.setString(1, dto.getProduct_no());
		pstmt.setString(2, dto.getProduct_name());
		pstmt.setInt(3, dto.getPrice());
		pstmt.setInt(4, dto.getEa());
		pstmt.setString(5, dto.getMaker());
		pstmt.executeUpdate();
		
		manager.close(null, pstmt);
	}
	
	// 상품 목록 출력
	public ArrayList<ProductDTO> selectProductAllList(){
		String sql = "select * from product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
				list.add(new ProductDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(rs, pstmt);
		}
		return list;
	}
	
	// 상품 검색
	public ProductDTO selectProduct(String product_no) {
		ProductDTO dto = null;
		String sql = "select * from product where product_no like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, product_no);
			rs = pstmt.executeQuery();
			if(rs.next())
				dto = new ProductDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
}
