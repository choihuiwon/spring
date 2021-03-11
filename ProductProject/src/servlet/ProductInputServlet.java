package servlet;

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

import dao.ProductDAO;
import dto.ProductDTO;

@WebServlet("/input.do")
public class ProductInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductInputServlet() {
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		try {
			// 데이터 받아오기
			String product_no = request.getParameter("product_no");
			String product_name = request.getParameter("product_name");
			String maker = request.getParameter("maker");
			// 데이터 검사
			if(product_no == "" || product_name == "" || maker == "")
				throw new Exception("1001");
			if(product_no.length()>12 || product_name.length()>20 || maker.length() > 20)
				throw new Exception("1001");
			// 데이터 받아오기
			int price = Integer.parseInt(request.getParameter("price"));
			int ea = Integer.parseInt(request.getParameter("ea"));
			// 데이터 검사
			if(price < 0 || ea < 0)
				throw new Exception("1002");
			
			// 상품 등록
			ProductDAO.getInstance().insertProduct(new ProductDTO(product_no, product_name, price, ea, maker));
			
			// 상품 목록 출력
			ArrayList<ProductDTO> list = ProductDAO.getInstance().selectProductAllList();
			JSONArray arr = new JSONArray(list);
			JSONObject obj = new JSONObject();
			obj.put("result", arr);
			response.getWriter().write(obj.toString());
		} catch (SQLException e) {
			response.setStatus(1001);
			response.getWriter().write("입력한 데이터 오류");
		} catch (Exception e) {
			if(e.getMessage().equals("1001")) {
				response.setStatus(1001);
				response.getWriter().write("입력한 데이터 오류");
			}
			else if(e.getMessage().equals("1002")) {
				response.setStatus(1002);
				response.getWriter().write("금액과 재고는 양수만 입력하세요.");
				response.setStatus(1002);
				response.getWriter().write("금액과 재고는 양수만 입력하세요.");
			}
			else {
				response.setStatus(1003);
				response.getWriter().write("금액과 재고는 숫자로 입력하세요.");
			}
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
