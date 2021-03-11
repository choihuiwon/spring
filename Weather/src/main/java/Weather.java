import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

public class Weather {
	public String getWeather(String nx, String ny) {
		String weather = "";
		String serviceKey, dataType, base_time, base_date, numOfRows, pageNo, url;
		nx = "57";
		ny = "127";	// 서울
		url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		serviceKey = "de4n60BId3f9KozHqu47z%2FtxC6YjJEtG0KeMQojtPltNyV702A9d5lltXnQdN7W25Q9R71S0krGaTtdfEIEoQw%3D%3D";
		dataType = "json";
		pageNo = "1";
		numOfRows = "10";
		base_time = "0200";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		base_date = sdf.format(cal.getTime());
		String apiUrl = url + "?serviceKey="+serviceKey+"&base_date="+base_date + "&base_time=" + base_time+"&nx="+nx+"&ny="+ny+"&numOfRows="+numOfRows+"&pageNo=1&dataType="+dataType;
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
//			System.out.println(result);
			
			// json으로 뽑기 response(obj) - body(obj) - items(obj) -item(array)
			JSONObject json = new JSONObject(result);
			JSONArray arr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
			for(int i=0; i<arr.length(); i++) {
				String category = arr.getJSONObject(i).getString("category");
				switch(category) {
				case "T3H":
					weather += "기온 : " + arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("baseTime")+"\n";
					break;
				case "TMX":
					weather += "최고기온 : " + arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("baseTime")+"\n";
					break;
				case "TMN":
					weather += "최저기온 : " + arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("baseTime")+"\n";
					break;
				case "POP":
					weather += "강수확률 : " + arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("baseTime")+"\n";
					break;
				}
			}
				
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weather;
	}
}
