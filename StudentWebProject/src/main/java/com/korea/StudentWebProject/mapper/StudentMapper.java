package com.korea.StudentWebProject.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korea.StudentWebProject.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	List<StudentDTO> selectAllStudent();

	List<StudentDTO> searchStudent(HashMap<String, Object> map);

	int insertLog(HashMap<String, Object> map);

}
