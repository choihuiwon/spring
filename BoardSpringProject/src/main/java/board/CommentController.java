package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.CommentDto;
import board.service.CommentService;

@Controller
public class CommentController {
	private CommentService service;

	public CommentController(CommentService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("insertComment.do")
	public String insertComment(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		int result = service.insertComment(new CommentDto(bno, content, writer));
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("commentLikeHate.do")
	public String commentLikeHate(HttpServletRequest request, HttpServletResponse response) {
		int cno = Integer.parseInt(request.getParameter("cno"));
		String mode = request.getParameter("mode");
		
		int result = service.commentLikeHate(cno, mode);
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("commentLikeSort.do")
	public String commentLikeSort(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		List<CommentDto> list = service.getCommentListSortLike(bno);
		System.out.println(list.toString());
		response.setContentType("text/html;charset=utf-8");
		JSONArray array = new JSONArray(list);
		JSONObject obj = new JSONObject();
		obj.put("result", array);
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("commentHateSort.do")
	public String commentHateSort(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		List<CommentDto> list = service.getCommentListSortHate(bno);
		response.setContentType("text/html;charset=utf-8");
		JSONArray array = new JSONArray(list);
		JSONObject obj = new JSONObject();
		obj.put("result", array);
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
