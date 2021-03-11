package gocamping;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;

public class ApiExplorer2 {
	public static void main(String[] args) {
		// 검색어
				String search = "운영";
				
				try {
					StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/searchList"); /*URL*/
					urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=de4n60BId3f9KozHqu47z%2FtxC6YjJEtG0KeMQojtPltNyV702A9d5lltXnQdN7W25Q9R71S0krGaTtdfEIEoQw%3D%3D"); /*Service Key*/
					urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("de4n60BId3f9KozHqu47z%2FtxC6YjJEtG0KeMQojtPltNyV702A9d5lltXnQdN7W25Q9R71S0krGaTtdfEIEoQw%3D%3D", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
					urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지번호*/
					urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
					urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(윈도우폰),ETC*/
					urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
					urlBuilder.append("&" + URLEncoder.encode("keyword","UTF-8") + "=" + URLEncoder.encode(search, "UTF-8")); /*검색 요청할 키워드(인코딩 필요)*/
					urlBuilder.append("&_type=json"); /*서비스명=어플명*/
					
					URL url = new URL(urlBuilder.toString());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Content-type", "application/json");
					BufferedReader rd;
					if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
						rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					} else {
						rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
					}
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = rd.readLine()) != null) {
						sb.append(line);
					}
					
		 			JSONObject json = new JSONObject(sb.toString());
					System.out.println(json.toString());
					ArrayList<SpotDTO> list = new ArrayList<SpotDTO>();
					
					if(conn.getResponseCode() == 200) {
						try {
							// item이 jsonobject일 경우
							JSONObject check = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item");
							
						}catch(Exception e) {
							// item이 jsonarray일 경우
							JSONArray arr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
							for(int i=0; i<arr.length(); i++) {
								JSONObject j = arr.getJSONObject(i);
								int contentId = j.has("contentId") ? j.getInt("contentId") : 1;
								String facltNm = j.has("facltNm") ? j.getString("facltNm") : "-";
								String lineIntro = j.has("lineIntro") ? j.getString("lineIntro") : "-";
								String intro = j.has("intro") ? j.getString("intro") : "-";
								String bizrno = j.has("bizrno") ? j.getString("bizrno") : "-";
								String manageSttus = j.has("manageSttus") ? j.getString("manageSttus") : "-";
								String hvofBgnde = j.has("hvofBgnde") ? j.getString("hvofBgnde") : "-";
								String hvofEnddle = j.has("hvofEnddle") ? j.getString("hvofEnddle") : "-";
								String featureNm = j.has("featureNm") ? j.getString("featureNm") : "-";
								String induty = j.has("induty") ? j.getString("induty") : "-";
								String lctCl = j.has("lctCl") ? j.getString("lctCl") : "-";
								String doNm = j.has("doNm") ? j.getString("doNm") : "-";
								String sigunguNm = j.has("sigunguNm") ? j.getString("sigunguNm") : "-";
								int zipcode = j.has("zipcode") ? j.getInt("zipcode") : 1;
								String addr1 = j.has("addr1") ? j.getString("addr1") : "-";
								String addr2 = j.has("addr2") ? j.getString("addr2") : "-";
								String tel = j.has("tel") ? j.getString("tel") : "-";
								String homepage = j.has("homepage") ? j.getString("homepage") : "-";
								int gnrlSiteCo = j.has("gnrlSiteCo") ? j.getInt("gnrlSiteCo") : 1;
								int autoSiteCo = j.has("autoSiteCo") ? j.getInt("autoSiteCo") : 1;
								int glampSiteCo = j.has("glampSiteCo") ? j.getInt("glampSiteCo") : 1;
								int caravSiteCo = j.has("caravSiteCo") ? j.getInt("caravSiteCo") : 1;
								int indvdlCaravSiteCo = j.has("indvdlCaravSiteCo") ? j.getInt("indvdlCaravSiteCo") : 1;
								int siteBottomCl1 = j.has("siteBottomCl1") ? j.getInt("siteBottomCl1") : 1;
								int siteBottomCl2 = j.has("siteBottomCl2") ? j.getInt("siteBottomCl2") : 1;
								int siteBottomCl3 = j.has("siteBottomCl3") ? j.getInt("siteBottomCl3") : 1;
								int siteBottomCl4 = j.has("siteBottomCl4") ? j.getInt("siteBottomCl4") : 1;
								int siteBottomCl5 = j.has("siteBottomCl5") ? j.getInt("siteBottomCl5") : 1;
								String glampInnerFclty = j.has("glampInnerFclty") ? j.getString("glampInnerFclty") : "-";
								String caravInnerFclty = j.has("caravInnerFclty") ? j.getString("caravInnerFclty") : "-";
								String operPdCl = j.has("operPdCl") ? j.getString("operPdCl") : "-";
								String trlerAcmpnyAt = j.has("trlerAcmpnyAt") ? j.getString("trlerAcmpnyAt") : "-";
								String caravAcmpnyAt = j.has("caravAcmpnyAt") ? j.getString("caravAcmpnyAt") : "-";
								int toiletCo = j.has("toiletCo") ? j.getInt("toiletCo") : 1;
								int swrmCo = j.has("swrmCo") ? j.getInt("swrmCo") : 1;
								int wtrplCo = j.has("wtrplCo") ? j.getInt("wtrplCo") : 1;
								String brazierCl = j.has("brazierCl") ? j.getString("brazierCl") : "-";
								String sbrsCl = j.has("sbrsCl") ? j.getString("sbrsCl") : "-";
								String sbrsEtc = j.has("sbrsEtc") ? j.getString("sbrsEtc") : "-";
								String posblFcltyCl = j.has("posblFcltyCl") ? j.getString("posblFcltyCl") : "-";
								String posblFcltyEtc = j.has("posblFcltyEtc") ? j.getString("posblFcltyEtc") : "-";
								String exprnProgrm = j.has("exprnProgrm") ? j.getString("exprnProgrm") : "-";
								String themaEnvrnCl = j.has("themaEnvrnCl") ? j.getString("themaEnvrnCl") : "-";
								String eqpmnLendCl = j.has("eqpmnLendCl") ? j.getString("eqpmnLendCl") : "-";
								String animalCmgCl = j.has("animalCmgCl") ? j.getString("animalCmgCl") : "-";
								String tourEraCl = j.has("tourEraCl") ? j.getString("tourEraCl") : "-";
								String firstImageUrl = j.has("firstImageUrl") ? j.getString("firstImageUrl") : "-";
								// 별점 평균
								double star = 0;
								//리뷰수
								int review_count = 0;
								
								// 리스트에 추가
								list.add(
										new SpotDTO(contentId, facltNm, lineIntro, intro, bizrno, manageSttus, hvofBgnde, hvofEnddle, featureNm, induty, lctCl, doNm, sigunguNm, zipcode, addr1, addr2, tel, homepage, gnrlSiteCo, autoSiteCo, glampSiteCo, caravSiteCo, indvdlCaravSiteCo, siteBottomCl1, siteBottomCl2, siteBottomCl3, siteBottomCl4, siteBottomCl5, glampInnerFclty, caravInnerFclty, operPdCl, trlerAcmpnyAt, caravAcmpnyAt, toiletCo, swrmCo, wtrplCo, brazierCl, sbrsCl, sbrsEtc, posblFcltyCl, posblFcltyEtc, exprnProgrm, themaEnvrnCl, eqpmnLendCl, animalCmgCl, tourEraCl, firstImageUrl, star, review_count)
										);
							}
							
						}
						
					} else {
					}
					
					rd.close();
					conn.disconnect();
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
	}
}
