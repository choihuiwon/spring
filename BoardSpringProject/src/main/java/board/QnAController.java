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

import board.dto.QnADto;
import board.service.QnAService;

@Controller
public class QnAController {
	private QnAService service;

	public QnAController(QnAService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("qnaView.do")
	public String qnaView(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int pageNo = 1;
		if(request.getParameter("pageNo") != null)
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String id = (String) session.getAttribute("id");
		String grade= (String) session.getAttribute("grade");
		try {
			if(id == null)
				response.getWriter().append("<script>location.href='login.do';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<QnADto> list = service.selectQnAList(id, pageNo, grade);
		request.setAttribute("list", list);
		return "qna/qnaView";
	}
	
	@RequestMapping("nextQnAList.do")
	public String nextQnAList(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 1. 조회할 페이지 번호 읽어옴
			int pageNo = 1;
			if(request.getParameter("pageNo") != null)
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			String id = (String) request.getSession().getAttribute("id");
			String grade= (String) request.getSession().getAttribute("grade");
			if(id == null)
				response.getWriter().append("<script>location.href='login.do';</script>");
			// 2. 해당 페이지 목록을 읽어옴
			List<QnADto> list = service.selectQnAList(id, pageNo, grade);
			// 3. 다음 페이지 번호 다음페이지가 없으면 0
			if(service.selectQnAList(id, pageNo+1, grade).size() == 0) 
				pageNo = 0;
			else 
				pageNo++;
			
			// 4. json으로 변환(qnadto, 다음페이지 번호)
			JSONObject result = new JSONObject();
			JSONArray arr = new JSONArray(list);
			result.put("nextPage", pageNo);
			result.put("list", arr);
			result.put("grade", grade);
			result.put("grade_name", request.getSession().getAttribute("grade_name"));
			// 5. writer로 출력
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("responseQnA.do")
	public String responseQnA(HttpServletRequest request, HttpServletResponse response) {
		int qno = Integer.parseInt(request.getParameter("qno"));
		String res = request.getParameter("res");
		int count = service.responseQnA(qno, res);
		try {
			response.getWriter().write(count+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("readQnA.do")
	public String readQnA(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if(!session.getAttribute("grade_name").equals("MASTER")) return null;
		int qno = Integer.parseInt(request.getParameter("qno"));
		int count = service.readQnA(qno);
		try {
			response.getWriter().write(count+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("sendQnA.do")
	public String sendQnA(HttpServletRequest request, HttpServletResponse response) {
		try {
			String writer = (String) request.getSession().getAttribute("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
	
			if(writer == null || title == null || content == null)
					response.getWriter().append("<script>alert('데이터를 전부 입력해 주세요.');history.back();</script>");
			
			QnADto dto = new QnADto(title, content, writer);
			
			int count = service.sendQnA(dto);
	
			if(count == 0)
				response.getWriter().append("<script>alert('문의 등록 실패');location.href='qnaView.do';</script>");
			else
				response.getWriter().append("<script>alert('문의 등록 성공');location.href='qnaView.do';</script>");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return null;
	}
}
