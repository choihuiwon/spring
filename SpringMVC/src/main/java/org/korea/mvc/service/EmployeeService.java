package org.korea.mvc.service;

import java.util.ArrayList;

import org.korea.mvc.dao.EmployeeDao;
import org.korea.mvc.dto.EmployeeDto;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	private EmployeeDao dao;
	
	public EmployeeService(EmployeeDao dao) {
		this.dao = dao;
		System.out.println("EmployeeService 생성");
	}
	
	// 로그인
	public EmployeeDto login(String eno, String name) {
		return dao.login(eno, name);
	}
	
	// 사원 정보 리스트
	public ArrayList<EmployeeDto> getAllEmployeeList(){
		return dao.getAllEmployeeList();
	}
}
