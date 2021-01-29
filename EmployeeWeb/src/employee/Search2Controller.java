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

import dao.EmployeeDao;
import dao.EmployeeDao2;
import dto.EmployeeDto;

/**
 * Servlet implementation class Search2Controller
 */
@WebServlet("/search2.do")
public class Search2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search2Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		ArrayList<EmployeeDto> list;
		try {
			EmployeeDao2 dao = new EmployeeDao2();
			list = dao.selectSearchNameEmployeeList(name);
			JSONArray jsonArray = new JSONArray(list);
			response.getWriter().write(jsonArray.toString());
		} catch (SerialException e) {
			e.printStackTrace();
			response.sendError(444,"에러 메세지");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
