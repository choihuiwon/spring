import java.util.Scanner;

public class NaverSearchMain {
// 검색어 입력받는 클래스
	public static void main(String[] args) {
		// 검색할 검색어 입력
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 입력 : ");
    	String text = sc.nextLine();
    	System.out.println(NaverSearchRun.getInstance().searchBlog(text));
    	sc.close();
	}

}
