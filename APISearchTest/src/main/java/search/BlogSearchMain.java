package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class BlogSearchMain {
	 public static void main(String[] args) {
	        String clientId = "n7ilN3l_pFD1t6kZPkmb"; //애플리케이션 클라이언트 아이디값"
	        String clientSecret = "Cn0QTO_K72"; //애플리케이션 클라이언트 시크릿값"
	        Scanner sc = new Scanner(System.in);
	        String text = null;
	        try {
	        	System.out.print("검색어 입력 : ");
	        	String input = sc.nextLine();
	            text = URLEncoder.encode(input, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("검색어 인코딩 실패",e);
	        }

	        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&sort=date";    // json 결과
	        URL url;
			try {
				url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if(responseCode==200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {  // 에러 발생 -log처리
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String result = "";
				while(true) {
					String str = br.readLine();
					if(str == null) break;
					result += str;
				}
				System.out.println(result);
				JSONObject json = new JSONObject(result);
				JSONArray arr = new JSONArray(json.getJSONArray("items"));
				for(int i=0; i<arr.length(); i++) {
					System.out.println(arr.getJSONObject(i).getString("title"));
					System.out.println(arr.getJSONObject(i).getString("link"));
					System.out.println(arr.getJSONObject(i).getString("description"));
				}
					
				
				sc.close();
				br.close();
				con.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
        }

	    private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }

	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
}
