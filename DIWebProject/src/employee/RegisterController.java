package employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import dto.EmployeeDto;

import config.DBManager;
import dao.EmployeeDao;
import di.DiContainer;

@WebServlet("/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String eno = request.getParameter("eno");
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		EmployeeDao dao = new EmployeeDao(DiContainer.getContext().getBean("manager", DBManager.class));
		// dao 보내기전 데이터 검증 수행
		try {
			if(eno.length()!=4) throw new Exception("1001");
			EmployeeDto dto = DiContainer.getContext().getBean("employeeDto",EmployeeDto.class);
			dto.init(eno, name, department, position, 0);
			dao.insertEmployee(dto);
			ArrayList<EmployeeDto> list = dao.selectEmployeeAllList();
			JSONArray arr = new JSONArray(list);	//json으로 변형
			JSONObject obj = new JSONObject();
			obj.put("result", arr);
			response.getWriter().write(obj.toString());
		} catch (ServletException e) {
			response.setStatus(1003);
			response.getWriter().write("서블릿 오류");
		} catch (SQLException e) {
			response.setStatus(1002);
			response.getWriter().write("입력한 데이터가 잘못되었습니다.");
		} catch (Exception e) {
			response.setStatus(1001);
			response.getWriter().write("사번은 4자리 입니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
