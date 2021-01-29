package com.db.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.db.dto.EmployeeDto;
import com.db.mapper.EmployeeMapper;

@Service
public class EmployeeService {
	private EmployeeMapper mapper;
	
	public EmployeeService(EmployeeMapper mapper) {
		super();
		this.mapper = mapper;
	}
	
	// 사원 정보 리스트
	public List<EmployeeDto> selectAllEmployee(){
		return mapper.selectAllEmployee();
	}

	public int insertEmployee(EmployeeDto dto) {
		return mapper.insertEmployee(dto);
	}

	public int updateEmployee(EmployeeDto dto) {
		return mapper.updateEmployee(dto);
	}

	public int deleteEmployee(String eno) {
		return mapper.deleteEmployee(eno);
	}
	
}
