수정 내역을 작성하시오.
1. EmployeeDTO 에 department 타입을 String 에서 int로 변경
2. pom.xml에 json추가
 <dependencies>
  	<dependency>
		    <groupId>org.json</groupId>
		    <artifactId>json</artifactId>
		    <version>20201115</version>
		</dependency>
  </dependencies>
3. DBManager클래스에서
	connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.1.1:1521:xe", "scott", "tiger"); 를
	connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger"); 로 수정
4. employee_manager.jsp 에서 ajax url 을 search.do로 수정

5. EmployeeMainServlet.java에서 인코딩 추가
	doGet에 추가 => response.setContentType("text/html; charset = utf-8");
	doPost에 추가 => request.setCharacterEncoding("UTF-8");