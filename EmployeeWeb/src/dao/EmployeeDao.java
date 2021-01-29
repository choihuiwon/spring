package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;

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

		// 사원정보 검색 리스트 (이름)
				public ArrayList<EmployeeDto> selectSearchNameEmployeeList(String name) throws SerialException{
					String sql = "select e.eno, e.name, e.department, p.name, s.salary from employee e, position_list p, employee_salary s where e.position like p.pno and s.eno like e.eno and e.name like ?";
					name = "%" + name + "%";
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
					try {
						pstmt = manager.getConnection().prepareStatement(sql);
						pstmt.setString(1, name);
						rs = pstmt.executeQuery();
						while (rs.next()) {
							list.add(new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DBManager.getInstance().close(rs, pstmt);
					}
					if(list.size() == 0)
						throw new SerialException("데이터가 없습니다.");
					return list;
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
		
		// 사원정보 리스트
		public ArrayList<EmployeeDto> selectEmployeeAllList(){
			String sql = "select e.eno, e.name, e.department, p.name from employee e, position_list p where e.position like p.pno";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
			try {
				pstmt = manager.getConnection().prepareStatement(sql);
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
		
		// 사원 등록
		public void insertEmployee(EmployeeDto dto) throws ServletException, SQLException {
			String sql = "insert into employee values(?,?,?,?)";
			PreparedStatement pstmt = null;
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, dto.getEno());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getDepartment());
			pstmt.setString(4, dto.getPosition());
			pstmt.executeUpdate();
		}
		
}

























