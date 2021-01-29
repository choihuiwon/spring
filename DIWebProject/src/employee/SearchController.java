package employee;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.json.JSONArray;
import dto.EmployeeDto;

import dao.EmployeeDao;
import di.DiContainer;

@WebServlet("/search.do")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		ArrayList<EmployeeDto> list;
		EmployeeDao dao = DiContainer.getContext().getBean(EmployeeDao.class);
		try {
			list = dao.selectSearchNameEmployeeList(name);
			JSONArray jsonArray = new JSONArray(list);
			response.getWriter().write(jsonArray.toString());
		} catch (SerialException e) {
			e.printStackTrace();
			response.sendError(444,"에러 메세지");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
