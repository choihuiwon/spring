import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherTestMain {
	public static void main(String[] args) {
		String nx, ny, serviceKey, dataType, base_time, base_date, numOfRows, pageNo, url;
		nx = "57";
		ny = "127";	// 서울
		url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		serviceKey = "de4n60BId3f9KozHqu47z%2FtxC6YjJEtG0KeMQojtPltNyV702A9d5lltXnQdN7W25Q9R71S0krGaTtdfEIEoQw%3D%3D";
		dataType = "json";
		pageNo = "1";
		numOfRows = "10";
		int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		int[] arr = new int[] {2,5,8,11,14,17,20,23};
		int i;
		for(i=0; i<arr.length; i++) {
			if(arr[i] > time) break;
		}
		if(i > 0) {	// 23시 이하
			i--;
		}
		else {	// 02시 이상
			i = arr.length-1;
			cal.add(Calendar.DATE, -1);
		}
		base_date = sdf.format(cal.getTime());
		base_time = arr[i] + "00";
		System.out.println(base_date + ","+base_time);
		
		String apiUrl = url + "?serviceKey="+serviceKey+"&base_date="+base_date + "&base_time=" + base_time+"&nx="+nx+"&ny="+ny+"&numOfRows=10&pageNo=1&dataType="+dataType;
		try {
			URL u = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			String result = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			System.out.println(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
