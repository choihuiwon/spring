package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.MemberDTO;
import board.service.AdminService;

@Controller
public class AdminManageController {

	private AdminService service;

	public AdminManageController(AdminService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("adminManageMemberView.do")
	public String adminManageMemberView(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 세션 유효 처리
		if(session.getAttribute("login") == null || (boolean)session.getAttribute("login") == false || !session.getAttribute("id").equals("admin")) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('관리자만 이용할 수 있습니다.');location.href='login.do';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			List<MemberDTO> list = service.selectAllMember();
			request.setAttribute("list", list);
			return "admin/manage_member_view";
		}
		return null;
	}
	
	@RequestMapping("adminUpdateMember.do")
	public String adminUpdateMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			int grade = Integer.parseInt(request.getParameter("grade"));
			int count = service.adminUpdateMember(id, name, age, grade);
			if(count == 0) 
					response.getWriter().print("false");
			else 
				response.getWriter().print("true");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			try {
				response.getWriter().print("false");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping("adminDeleteMember.do")
	public String adminDeleteMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			int count = service.adminDeleteMember(id);
			if(count == 0) 
					response.getWriter().print("false");
			else 
				response.getWriter().print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("adminSearchMember.do")
	public String adminSearchMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			String kind = request.getParameter("kind");
			String search = request.getParameter("search");
			List<MemberDTO> list = service.adminSearchMember(kind, search);
			response.setContentType("text/html;charset=utf-8");
			JSONArray array = new JSONArray(list);
			JSONObject obj = new JSONObject();
			obj.put("result", array);
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
