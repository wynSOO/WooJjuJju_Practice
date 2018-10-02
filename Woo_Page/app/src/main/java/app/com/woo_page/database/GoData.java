package app.com.woo_page.database;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class GoData extends AsyncTask{
    String goApiKey = GoDataKey.GO_API_KEY;
    String url = GoDataKey.GO_API_URL;
    StringBuilder goDataXml;
    JSONArray jsonArray;
    boolean exeFlag = false;
    List<goDataVO> goDataLists ;

    public void getData(){

        goDataLists = new ArrayList<>();

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(url);

        try {
            // 필수코드
            urlBuilder.append("?" + URLEncoder.encode("crtiKey","UTF-8") + "=" + goApiKey); /**/
            urlBuilder.append("&" + URLEncoder.encode("callTp","UTF-8") + "=" + URLEncoder.encode("L","UTF-8"));//"호출할 페이지 타입을 반드시 설정합니다.(L: 목록, D:상세)", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1","UTF-8"));//"기본값 1, 최대 1000 검색 시작위치를 지정할 수 있습니다", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10","UTF-8"));//"출력건수, 기본값 10, 최대 100 까지 가능합니다.", "UTF-8"));

            // 선택코드
//            urlBuilder.append("&" + URLEncoder.encode("srchKeyCode","UTF-8") + "=" + URLEncoder.encode("001","UTF-8"));//"001 제목 002 내용 003 제목+내용 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("lifeArray","UTF-8") + "=" + URLEncoder.encode("001","UTF-8"));//"001, 영유아 002, 아동 003, 청소년 004, 청년 005, 중장년 006, 노년 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("charTrgterArray","UTF-8") + "=" + URLEncoder.encode("004","UTF-8"));//"001, 해당없음 002, 여성 003, 임산부 004, 장애 005, 국가유공자등 보훈대상자 006, 실업자 ", "UTF-8")); /**/

//            urlBuilder.append("&" + URLEncoder.encode("searchWrd","UTF-8") + "=" + URLEncoder.encode("검색어", "UTF-8")); /**/
            /**/
//            urlBuilder.append("&" + URLEncoder.encode("obstKiArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 10, 지체 20, 시각 30, 청각 40, 언어 50, 지적 60, 뇌병변 70, 자폐성 80, 정신 90, 신장 A0, 심장 B0, 호흡기 C0, 간 D0, 안면 E0, 장루 F0, 간질 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("obstLvArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 1, 1급 2, 2급 3, 3급 4, 4급 5, 5급 6, 6급 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("trgterIndvdlArray","UTF-8") + "=" + URLEncoder.encode("001, 해당없음 002, 한부모 003, 다문화 004, 조손 005, 새터민 006, 소년소녀가장 007, 독거노인 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("desireArray","UTF-8") + "=" + URLEncoder.encode("0000000, 안전 1000000, 건강 2000000, 일상생활유지 3000000, 가족관계 4000000, 사회적 관계 5000000, 경제 6000000, 교육 7000000, 고용 8000000, 생활환경 9000000, 법률 및 권익보장 A000000, 기타 ", "UTF-8")); /**/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
           // conn.setRequestProperty("Content-type", "application/json");

            Log.d("Response code: " , ":"+conn.getResponseCode());
            //System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

           goDataXml = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                goDataXml.append(line);
            }

            rd.close();
            conn.disconnect();
            Log.d("Data",goDataXml.toString());
//            System.out.println(sb.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 백그라운드에서 작동될 코드를 호출하는 곳
    // doInBackground에서는 View에 어떠한 값도 읽거나 쓰면 안된다.
    // return 형을 Object에서 List<GoDataVO>로 변경
    @Override
    protected List<goDataVO> doInBackground(Object[] objects) {
        exeFlag = false;
        this.getData();

        // post에 있던 코드 이동
        XmlToJson xmlToJson = new XmlToJson.Builder(goDataXml.toString()).build();
        Log.d("JSON",xmlToJson.toString());

        JSONObject jsonObject = xmlToJson.toJson();
        try {
            JSONObject wanted = jsonObject.getJSONObject("wantedList");
            jsonArray = wanted.getJSONArray("servList");

            for (int i = 0 ; i<jsonArray.length();i++){
                Log.d("GetData",jsonArray.get(i).toString());

                // jsonArray를 List로 변환시키기
                goDataVO vo = new goDataVO();
                JSONObject js = jsonArray.getJSONObject(i);
                vo.servId = js.getString("servID");
                vo.servNm = js.getString("servNm");
                vo.jurMnofNm = js.getString("jurMnofNm");
                vo.jurOrgNm = js.getString("jurOrgNm");
                vo.inqNum = js.getString("inqNum");
                vo.servDgst = js.getString("servDgst");
                vo.servDtlLink = js.getString("servDtlLink");
                vo.svcfrstRegTs = js.getString("svcfrstRegTs");

                goDataLists.add(vo);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return goDataLists;
    }
}
