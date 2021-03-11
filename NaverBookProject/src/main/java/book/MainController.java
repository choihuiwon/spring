package book;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	AnnotationConfigApplicationContext context;
	
	public MainController() {
		context = new AnnotationConfigApplicationContext(DIContainer.class);
	}
	
	@RequestMapping("/")
	public String main() {
		return "book_search";
	}
	
	@RequestMapping("/search.do")
	public String search(HttpServletRequest request, HttpServletResponse response) {
		try {
			String text = request.getParameter("text");
			String start = request.getParameter("start");
			String display = request.getParameter("display");
			response.setContentType("text/html;charset=utf-8");
			JSONObject json = context.getBean(BookSearch.class).searchBook(text,start,display);
			json.put("title", request.getParameter("title"));
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
