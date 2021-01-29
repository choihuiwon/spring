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

import dao.EmployeeDao;
import dto.EmployeeDto;

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
		
		// dao 보내기전 데이터 검증 수행
		if(eno.length()!=4) {
			response.setStatus(1001);
			response.getWriter().write("사번은 4자리 입니다.");
		}else {
			try {
				EmployeeDao.getInstance().insertEmployee(new EmployeeDto(eno, name, department, position));
				ArrayList<EmployeeDto> list = EmployeeDao.getInstance().selectEmployeeAllList();
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
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
