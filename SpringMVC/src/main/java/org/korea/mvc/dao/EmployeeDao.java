package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;

import org.korea.mvc.dto.EmployeeDto;

import config.DBManager;

public class EmployeeDao {
		private DBManager manager;
		
		public EmployeeDao(DBManager manager) {
			super();
			this.manager = manager;
		}

		// 로그인
		public EmployeeDto login(String eno, String name) {
			EmployeeDto dto = null;
			String sql = "select * from employee where eno like ? and name like ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = manager.getConnection().prepareStatement(sql);
				pstmt.setString(1, eno);
				pstmt.setString(2, name);
				rs = pstmt.executeQuery();
				if(rs.next())
					dto = new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dto;
		}
		
		// 사원 정보 리스트
		public ArrayList<EmployeeDto> getAllEmployeeList(){
			ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
			String sql = "select e.eno, e.name, e.department, e.position, s.salary from employee e, employee_salary s where e.eno = s.eno";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = manager.getConnection().prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
					list.add(new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
}

























