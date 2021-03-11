package papago;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONObject;

public class PaPaGo {
	
	public String translatePaPaGo(String target, String source, String text) {
		
		String clientId = "n7ilN3l_pFD1t6kZPkmb";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "Cn0QTO_K72";//애플리케이션 클라이언트 시크릿값";
        
        JSONObject result = new JSONObject();
        
        try {
            text = URLEncoder.encode(text, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source="+source+"&target="+target+"&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생 -log처리
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            
            JSONObject res = new JSONObject(response.toString());
            
            if(responseCode != 200) {
            	FileWriter fw = new FileWriter("error.txt", true);
            	PrintWriter pw = new PrintWriter(fw);
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				Calendar today = Calendar.getInstance();
            	JSONObject obj = new JSONObject(response.toString());
            	String msg = sdf.format(today.getTime())+ "\t" + responseCode + "\t" + obj.getString("errorCode") + "\t" + obj.getString("errorMessage");
            	pw.println(msg);
            	pw.flush();
            	pw.close();
            	fw.close();
            }else {
            	result.put("responseCode", responseCode);
            	result.put("resultText", res.getJSONObject("message").getJSONObject("result").getString("translatedText"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
		return result.toString();
	}
}
