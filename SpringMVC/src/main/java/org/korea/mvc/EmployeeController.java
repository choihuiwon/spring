package org.korea.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.korea.mvc.dto.EmployeeDto;
import org.korea.mvc.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/employeeLoginAction.do")
	public String employeeMain(HttpServletRequest request, HttpSession session) {
		String eno = request.getParameter("eno");
		String name = request.getParameter("name");
		EmployeeDto dto = employeeService.login(eno, name);
		session.setAttribute("dto", dto);
		if(dto == null)
			return "login";
		else {
			ArrayList<EmployeeDto> list = employeeService.getAllEmployeeList();
			request.setAttribute("list", list);
			return "employee_manager";
		}
			
	}
}
