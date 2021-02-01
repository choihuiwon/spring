package board;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import board.service.MemberService;
@Controller
public class MainController {

	/*
	 * private MemberService service;
	 * 
	 * public MainController(MemberService service) { super(); this.service =
	 * service; }
	 */
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
}
