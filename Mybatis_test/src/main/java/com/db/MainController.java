package com.db;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.dto.EmployeeDto;
import com.db.service.EmployeeService;

@Controller
public class MainController {
	private EmployeeService employeeService;

	public MainController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/main.do")
	public String main(Model model) {
		List<EmployeeDto> list = employeeService.selectAllEmployee();
		model.addAttribute("list",list);
		return "main";
	}
	
	@RequestMapping("/register.do")
	public String insert(HttpServletRequest request, Model model) {
		String eno = request.getParameter("eno");
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		
		int count = employeeService.insertEmployee(new EmployeeDto(eno,name,department,position));
		System.out.println(count + "건 등록");
		return main(model);
	}
	
	@RequestMapping("/dataUpdate.do")
	public String dataUpdate(HttpServletRequest request, Model model) {
		String command = request.getParameter("command");
		String eno = request.getParameter("eno");
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		
		if(command.equals("update")) {
			int count = employeeService.updateEmployee(new EmployeeDto(eno, name, department, position));
			System.out.println(count + "건 수정");
		}
		else {
			int count = employeeService.deleteEmployee(eno);
			System.out.println(count + "건 삭제");
		}
		
		return main(model);
	}
}
