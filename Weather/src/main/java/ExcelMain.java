import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMain {

	public static void main(String[] args) {
		ZipSecureFile.setMinInflateRatio(0);	// 있는 내용을 다 받도록 설정 (파일 용량이 너무 크면 다운됨)
		try {
			FileInputStream fis = new FileInputStream("excel.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);	// excel파일 
			XSSFSheet sheet = workbook.getSheetAt(0);	// 엑셀 시트 번호로 읽어옴
			System.out.println("전체 행 개수 : " + sheet.getPhysicalNumberOfRows());
			System.out.println("해당 라인의 컬럼수 : " + sheet.getRow(0).getPhysicalNumberOfCells());
			System.out.println("4행 6열에 있는 값 : " + sheet.getRow(3).getCell(5));
			
			// 엑셀 파일의 전체 내용을 출력
//			for(int i=0; i<sheet.getPhysicalNumberOfRows(); i++) {
//				for(int j=0; j<sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
//					System.out.print(sheet.getRow(i).getCell(j) + "\t");
//				}
//				System.out.println();
//			}
			
			// 좌표값 검색해서 출력
			Scanner sc = new Scanner(System.in);
			System.out.println("시도 시군구 형식으로 입력하세요.");
			String address = sc.nextLine();
			String addr[] = address.split(" ");
			// 문자열 부분 일치
			
			String nx = "";
			String ny = "";
			for(int i=0; i<sheet.getPhysicalNumberOfRows(); i++) {
					if(!sheet.getRow(i).getCell(2).getStringCellValue().equals("")) continue;
					if(sheet.getRow(i).getCell(2).getStringCellValue().equals(addr[0])) {
						if(sheet.getRow(i).getCell(3).getStringCellValue().equals(addr[1])) {
							System.out.println(sheet.getRow(i).getCell(2) + " " + sheet.getRow(i).getCell(3) + " " + sheet.getRow(i).getCell(5) + " " + sheet.getRow(i).getCell(6));
							nx = sheet.getRow(i).getCell(5).getStringCellValue();
							ny = sheet.getRow(i).getCell(6).getStringCellValue();
						}
					}
			}
			// 날씨 정보 출력
			Weather w = new Weather();
			System.out.println(w.getWeather(nx, ny));
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
