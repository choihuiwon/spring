package com.korea.StudentWebProject.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.StudentWebProject.dto.StudentDTO;
import com.korea.StudentWebProject.mapper.StudentMapper;

@Service
public class StudentService {
	private StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<StudentDTO> selectAllStudent() {
		return mapper.selectAllStudent();
	}

	public List<StudentDTO> searchStudent(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.searchStudent(map);
	}

	public int insertLog(String log_date, int code_number, String message) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("log_date", log_date);
		map.put("code_number", code_number);
		map.put("message", message);
		return mapper.insertLog(map);
	}
	
	
}
