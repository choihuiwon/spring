package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.korea.mvc.dto.MemberDTO;

import config.DBManager;

public class MemberDAO {
	private DBManager manager;
	
	public MemberDAO(DBManager manager) {
		super();
		this.manager = manager;
	}
	
	// 로그인
	public MemberDTO login(String id, String passwd) {
		MemberDTO dto = null;
		String sql = "select * from member where id like ? and passwd like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next())
				dto = new MemberDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
}
