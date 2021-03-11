package papago;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {
	AnnotationConfigApplicationContext appContext;
	
	public MainController() {
		appContext = new AnnotationConfigApplicationContext(DIContainer.class);
	}

	@RequestMapping("/")
	public String main() {
		return "papago_main";
	}
	
	@RequestMapping("/translate.do")
	public String translate(HttpServletRequest request, HttpServletResponse response) {
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		String source = request.getParameter("source");
		String result = appContext.getBean(PaPaGo.class).translatePaPaGo(target, source, text);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
