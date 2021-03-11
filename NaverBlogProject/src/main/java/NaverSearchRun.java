import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverSearchRun {
	// 싱글톤 처리
	private static NaverSearchRun instance = new NaverSearchRun();

	private NaverSearchRun() {
	}
	
	public static NaverSearchRun getInstance() {
		if(instance == null)
			instance = new NaverSearchRun();
		return instance;
	}
	
	public String searchBlog(String text) {
		String blog = "";	// 콘솔에 출력할 결과값
		String clientId = "n7ilN3l_pFD1t6kZPkmb"; //애플리케이션 클라이언트 아이디값"
		System.out.println("여기까지");
        String clientSecret = "Cn0QTO_K72"; //애플리케이션 클라이언트 시크릿값"
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/blog?query" + text + "&sort=date";    // json 결과
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
			FileWriter fw = null;
			PrintWriter pw = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Calendar today = Calendar.getInstance();
			JSONObject obj = new JSONObject(result);
			System.out.println(result);
			if(responseCode == 200) {
				// 정상 호출시 콘솔에 문서제목, 링크, 블로그 이름 출력
				JSONObject json = new JSONObject(result);
				JSONArray arr = new JSONArray(json.getJSONArray("items"));
				for(int i=0; i<arr.length(); i++) {
					blog += "제목 : "+arr.getJSONObject(i).getString("title")+"\n";
					blog += "링크 : "+arr.getJSONObject(i).getString("link")+"\n";
					blog += "블로그 이름 : "+arr.getJSONObject(i).getString("bloggername")+"\n";
				}
				// 텍스트 파일에 기록
				fw = new FileWriter("blog_search.txt", true);
				pw = new PrintWriter(fw);
				String msg = sdf.format(today.getTime())+ "\n" + blog;
				pw.println(msg);
				pw.flush();
			}else {
				// 에러 발생시 exception.txt에 기록
				fw = new FileWriter("exception.txt", true);
				pw = new PrintWriter(fw);
				String msg = sdf.format(today.getTime())+ "\t" + responseCode + "\t" + obj.getString("errorCode") + "\t" + obj.getString("errorMessage");
				blog = msg;
				pw.println(msg);
				pw.flush();
			}
			if(pw != null) pw.close();
			if(fw != null) fw.close();
			br.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
     
		return blog;
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
