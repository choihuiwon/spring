package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;

import config.DBManager;
import dto.EmployeeDto;

public class EmployeeDao {
	// 싱글톤 패턴 적용
		private static EmployeeDao instance = new EmployeeDao();
		private DBManager manager;
		private EmployeeDao() {
			manager = DBManager.getInstance();
		}
		public static EmployeeDao getInstance() {
			if(instance == null)
				instance = new EmployeeDao();
			return instance;
		}
		
		// 과장급 이상인 사원 조회 210125
		public String selectPositionArea(int pno){
			String result = null;
			String sql = "select e.eno, e.name, e.department, p.name from employee e, position_list p where e.position like p.pno and e.position >= ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
			try {
				pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					list.add(new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				}
				JSONArray jsonArray = new JSONArray(list);
				result = jsonArray.toString();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.getInstance().close(rs, pstmt);
			}
			return result;
		}
		
		// 낮은 연봉 사원 5명 검색
		public String getLowSalary() {
			String result = null;
			ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
			String sql = "select e.eno, e.name, e.department, p.name, a.salary "
					   + "from EMPLOYEE e, position_list p, "
					   + "(select eno, salary from(select rownum, eno, salary, rank() over(order by salary asc) from EMPLOYEE_SALARY where rownum < 6)) a "
					   + "where e.eno like a.eno and e.position = p.pno";
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = manager.getConnection().prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					list.add(new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
				}
				JSONArray jsonArray = new JSONArray(list);
				result = jsonArray.toString();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.getInstance().close(rs, pstmt);
			}

			return result;
		}

		// 사원번호로 사원정보 정확하게 1건 거색하는 기능을 작업
		public EmployeeDto selectEmployee(String eno) {
			String sql = "select e.eno, e.name, e.department, p.name, s.salary "
					   + "from EMPLOYEE e, position_list p, employee_salary s "
					   + "where e.eno like s.eno and e.position = p.pno and e.eno like ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			EmployeeDto dto = null;
			try {
				pstmt = manager.getConnection().prepareStatement(sql);
				pstmt.setString(1, eno);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					dto = new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.getInstance().close(rs, pstmt);
			}
			return dto;
		}
		
		// 
		public ArrayList<EmployeeDto> selectEmployeeAllList(){
			String sql = "select e.eno, e.name, e.department, p.name from employee e, position_list p where e.position like p.pno";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
			try {
				pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					list.add(new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.getInstance().close(rs, pstmt);
			}
			return list;
		}
		
}

























