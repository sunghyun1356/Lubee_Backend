package com.Lubee.Lubee.location;

import com.Lubee.Lubee.enumset.Category;
import com.Lubee.Lubee.location.dto.SeoulLocationInfo;
import com.Lubee.Lubee.location.mapper.LocationMapper;
import com.Lubee.Lubee.location.repository.LocationRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationApiClient {

    @Value("${RESTAURANT_API_KEY}")
    private String res_api_Key;             // 서울시 일반음식점
    @Value("${CULTURE_API_KEY}")
    private String cul_api_Key;             // 서울시 문화 공간

    private static final String BASE_URL = "http://openapi.seoul.go.kr:8088/";
    private static final String RES_URL_SUFFIX = "/json/LOCALDATA_072404/";
    private static final String CUL_URL_SUFFIX = "/json/culturalSpaceInfo/";
    private static final int MAX_ITEMS = 1000;

    private int nData_res = 0;
    private Long totalData_res = 0L;
    private Long totalLocations_res = 0L;

    private int nData_cul = 0;
    private Long totalData_cul = 0L;
    private Long totalLocations_cul = 0L;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocationMapper locationMapper;

    /**
     * 서울시 일반 음식점
     */
    public void loadRestaurantLocation() {

        StringBuilder result;

        try {
            int startReq = 1;
            int endReq = MAX_ITEMS;
            boolean nextFinished = false;

            while (true) {
                URL url = new URL(BASE_URL + res_api_Key + RES_URL_SUFFIX + startReq + "/" + endReq);
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                result = new StringBuilder(br.readLine());
                String returnLine;

                while ((returnLine = br.readLine()) != null) {
                    result.append(returnLine).append("\n\r");
                }
                saveRestuarantLocation(result.toString());           // jsonParser 이용 후 DB 저장
                //System.out.println("totalData :  " + totalData_res);

                if(nextFinished){
                    break;
                }

                if (totalData_res + MAX_ITEMS > totalLocations_res) {       // 마지막 요청이면
                    nextFinished = true;
                }

                startReq += MAX_ITEMS;
                endReq += MAX_ITEMS;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveRestuarantLocation(String jsonData){

        List<SeoulLocationInfo> seoulLocationInfoList = new ArrayList<>();

        try {
            JSONObject jsonOb;
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);

            JSONObject localData = (JSONObject) jsonObject.get("LOCALDATA_072404");
            totalLocations_res = (long) localData.get("list_total_count");
            JSONArray jsonArray = (JSONArray) localData.get("row");

            for (Object o : jsonArray) {
                jsonOb = (JSONObject) o;

                SeoulLocationInfo seoulLocationInfo = SeoulLocationInfo.builder()
                        .name(jsonOb.get("BPLCNM").toString())      // 사업장명
                        .parcelBaseAddress(jsonOb.get("RDNWHLADDR").toString()) //도로명 주소
                        .x_coord(jsonOb.get("X").toString())
                        .y_coord(jsonOb.get("Y").toString())
                        .category(Category.RESTAURANT)
                        .build();
                seoulLocationInfoList.add(seoulLocationInfo);
                //locationRepository.save(locationMapper.toEntity(seoulLocationInfo));
            }
            nData_res = seoulLocationInfoList.size();
            totalData_res += nData_res;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 서울시 문화공간
     */
    public void loadCultureLocation() {

        StringBuilder result;

        try {
            int startReq = 1;
            int endReq = MAX_ITEMS;
            boolean nextFinished = false;

            while (true) {
                URL url = new URL(BASE_URL + cul_api_Key + CUL_URL_SUFFIX + startReq + "/" + endReq);
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                result = new StringBuilder(br.readLine());
                String returnLine;

                while ((returnLine = br.readLine()) != null) {      // JSON을 String형식으로 가져옴
                    result.append(returnLine).append("\n\r");
                }
                saveCultureLocation(result.toString());           // jsonParser 이용 후 DB 저장
                System.out.println("!!!!  totalData of culture :  " + totalData_cul);

                if (nextFinished) {
                    break;
                }

                if (totalData_cul + MAX_ITEMS > totalLocations_cul) {
                    nextFinished = true;
                }
                startReq += MAX_ITEMS;
                endReq += MAX_ITEMS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCultureLocation(String jsonData){

        List<SeoulLocationInfo> seoulLocationInfoList = new ArrayList<>();

        try {
            JSONObject jsonOb;
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);

            JSONObject localData = (JSONObject) jsonObject.get("culturalSpaceInfo");
            totalLocations_cul = (long) localData.get("list_total_count");
            //System.out.println("문화 총 개수 : " + totalLocations_cul);
            JSONArray jsonArray = (JSONArray) localData.get("row");

            for (Object o : jsonArray) {
                jsonOb = (JSONObject) o;
                SeoulLocationInfo seoulLocationInfo = SeoulLocationInfo.builder()
                        .name(jsonOb.get("FAC_NAME").toString())      // 문화시설명
                        .parcelBaseAddress(jsonOb.get("ADDR").toString()) // 주소
                        .x_coord(jsonOb.get("X_COORD").toString())
                        .y_coord(jsonOb.get("Y_COORD").toString())
                        .category(Category.CULTURE)
                        .build();
                seoulLocationInfoList.add(seoulLocationInfo);
                //System.out.println("SeoulLocationInfo :  " + seoulLocationInfo.getName());

                // DB 저장
                locationRepository.save(locationMapper.toEntity(seoulLocationInfo));
            }
            nData_cul = seoulLocationInfoList.size();
            totalData_cul += nData_cul;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 테스트용 코드 (데이터 잘 불러오는지)
     */
    public void laod_data(){

        StringBuilder result = new StringBuilder();

        try {
            int startReq = 1;
            int endReq = 5;

            URL url = new URL(BASE_URL + cul_api_Key + CUL_URL_SUFFIX + startReq + "/" + endReq);
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = new StringBuilder(br.readLine());
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine).append("\n\r");
            }

            //System.out.println("JSONDATA\n" + result.toString());

            // json
            JSONObject jsonOb;
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result.toString());

            JSONObject localData = (JSONObject) jsonObject.get("culturalSpaceInfo");
            JSONArray jsonArray = (JSONArray) localData.get("row");

            for (Object o : jsonArray) {
                jsonOb = (JSONObject) o;

                SeoulLocationInfo seoulLocationInfo = SeoulLocationInfo.builder()
                        .name(jsonOb.get("FAC_NAME").toString())      // 문화시설명
                        .parcelBaseAddress(jsonOb.get("ADDR").toString()) // 주소
                        .x_coord(jsonOb.get("X_COORD").toString())
                        .y_coord(jsonOb.get("Y_COORD").toString())
                        .build();
                //System.out.println(seoulLocationInfo.getName() + " : " + seoulLocationInfo.getParcelBaseAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
