package board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import board.service.MemberService;

//import board.service.MemberService;
@Controller
public class MainController {

	private MemberService service;

	public MainController(MemberService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/")
	public String main() {
		return "main";
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
