package board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.MemberDTO;
import board.service.MemberService;
@Controller
public class MainController {

	private MemberService service;
	
	public MainController(MemberService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/")
	public String main(Model model) {
		return "main";
	}
	
	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("loginAction.do")
	public String loginAction(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberDTO dto = service.login(id, pass);
		if(dto == null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('로그인 실패');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			request.getSession().setAttribute("dto", dto);
			return main(model);
		}
		return null;
	}

	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();;
		return "main";
	}
}
