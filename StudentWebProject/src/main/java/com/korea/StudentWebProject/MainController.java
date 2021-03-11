package com.korea.StudentWebProject;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.korea.StudentWebProject.dto.StudentDTO;
import com.korea.StudentWebProject.service.StudentService;

@Controller
public class MainController {
	private StudentService service;

	public MainController(StudentService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main(HttpServletRequest request) {
		List<StudentDTO> list = service.selectAllStudent();
		request.setAttribute("list", list);
		return "student_search";
	}
	
	// 검색
	@RequestMapping("studentSearch.do")
	public String studentSearch(HttpServletRequest request, HttpServletResponse response) {
		try {
			String kind = request.getParameter("kind");
			String search = request.getParameter("search");
			List<StudentDTO> list = service.searchStudent(kind, search);
			response.setContentType("text/html;charset=utf-8");
			JSONArray array = new JSONArray(list);
			JSONObject obj = new JSONObject();
			obj.put("result", array);
			if(list.size()>0) {
				obj.put("responseCode", 200);
				obj.put("responseMessage", "정상적으로 조회되었습니다.");
			}else {
				obj.put("responseCode", 500);
				obj.put("responseMessage", "조회된 데이터가 없습니다.");
			}
			// 검색 결과 출력
			System.out.println(obj.toString());
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("sendLog.do")
	public String sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int code_number = Integer.parseInt(request.getParameter("code_number"));
		String message = request.getParameter("message");
		System.out.println(log_date + " " + code_number + " " + message);
		int count = service.insertLog(log_date, code_number, message);
		System.out.println(count);
		try {
			response.getWriter().write("add count + " + count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
