package com.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.db.dto.EmployeeDto;


@Mapper
public interface EmployeeMapper {
	public List<EmployeeDto> selectAllEmployee();

	public int insertEmployee(EmployeeDto dto);

	public int updateEmployee(EmployeeDto dto);

	public int deleteEmployee(String eno);
	
}
