package com.korea.StudentWebProject;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.korea.StudentWebProject.dto.StudentDTO;
import com.korea.StudentWebProject.mapper.StudentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentWebProjectApplicationTests {
	
	@Autowired
	StudentMapper mapper;
	
	@Test
	void contextLoads() {
		System.out.println(mapper.selectAllStudent());
	}
	
	@DisplayName("학생정보 검색 테스트")
	@Test
	void searchStudent() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", "name");
		map.put("search","수");
		List<StudentDTO> list = mapper.searchStudent(map);
		for(StudentDTO l :list)
			System.out.println(l.toString());
	}

}
