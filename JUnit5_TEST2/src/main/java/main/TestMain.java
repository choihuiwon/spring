package main;

import java.util.Scanner;


import dao.EmployeeDao;

public class TestMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String result = "";
		System.out.println("특정 직급 이상 사원 조회");
		System.out.print("검색할 직급 : ");
		int qno = sc.nextInt();
		result = EmployeeDao.getInstance().selectPositionArea(qno);
		System.out.println(result);
		
		System.out.println("===================");
		result = EmployeeDao.getInstance().getLowSalary();
		System.out.println(result);
		
	}
}
