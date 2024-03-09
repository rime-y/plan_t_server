package com.plantplus.plantplus.service;

import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoHealthResDto;
import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoHealthResV3Dto;

import java.util.*;

public class PlantService {

    // webclient와 plant 이름 전달 받은 후
    // 한명 / 세부 정보 불러옴
    public Map<String, String> getPlantInfo(String name, WebClientService webClientService, String lang){
        PlantInfoService plantInfoService = new PlantInfoService(name, webClientService, lang);
        String res = "";
        Map<String, String> result = null;
        String plntNmKr = "";

        if (plantInfoService.getSearched() && (plantInfoService.getPlantNameKor() != null)){
            String info1 = plantInfoService.getPlantInfo().get("fmlCodeNm");
            plntNmKr = plantInfoService.getPlantNameKor();
            res = "이름: "+plntNmKr+" 과명: "+info1;
            result = plantInfoService.getPlantInfo();
            result.put("plantName", plntNmKr);
        } else {
            res = "검색 결과 없음";
        }

        System.out.println("getPlantInfo res: "+res);

        return result;
    }

    // 세부정보 종류 정리해서 메시지 용으로 깔끔하게 전달(Map 형태)
    public Map<String, String> orgPlantInfoMap(Map<String, String> data){
        Map<String, String> resMap = new HashMap<>();

        Map<String, String> labelMap = new HashMap<>(){{
            put("plantName", "식물명");
            put("plntbneNm", "학명");
            put("lighttdemanddoCodeNm", "요구 광도");
            put("postngplaceCodeNm", "배치 장소");
            put("fncltyInfo", "기능성 정보");
            put("watercycleSprngCodeNm", "물주기(봄)");
            put("watercycleSummerCodeNm", "물주기(여름)");
            put("watercycleAutumnCodeNm", "물주기(가을)");
            put("watercycleWinterCodeNm", "물주기(겨울)");
            put("hdCodeNm", "요구 습도");
            put("grwhTpCodeNm", "요구 생육온도");
        }};


        String target = "plantName";
        System.out.println("labelMap "+labelMap.keySet());
        for (String key : labelMap.keySet()) {
            target = labelMap.get(key);
            if (data.containsKey(key)){
                resMap.put(target, data.get(key));
            }
        }

        return resMap;
    }

    // 세부정보 종류 정리해서 메시지 용으로 깔끔하게 전달(String 형태)
    public List<String> orgPlantInfoString(Map<String, String> data){
        List<String> resString = new ArrayList<>();

        Map<String, String> labelMap = new HashMap<>(){{
            put("plantName", "식물 명");
            put("plntbneNm", "학명");
            put("lighttdemanddoCodeNm", "요구 광도");
            put("postngplaceCodeNm", "배치 장소");
            put("fncltyInfo", "기능성 정보");
            put("watercycleSprngCodeNm", "물주기(봄)");
            put("watercycleSummerCodeNm", "물주기(여름)");
            put("watercycleAutumnCodeNm", "물주기(가을)");
            put("watercycleWinterCodeNm", "물주기(겨울)");
            put("hdCodeNm", "요구 습도");
            put("grwhTpCodeNm", "요구 생육온도");
        }};

        // 내부 요소들 순서대로
        List<String> labelList = Arrays.asList(
                "plantName",
                "plntbneNm",
                "lighttdemanddoCodeNm",
                "postngplaceCodeNm",
                "fncltyInfo",
                "watercycleSprngCodeNm",
                "watercycleSummerCodeNm",
                "watercycleAutumnCodeNm",
                "hdCodeNm",
                "grwhTpCodeNm"
        );

        String target = "plantName";
        for (String key : labelList){
            target = labelMap.get(key);
            if (key == "plantName"){
                resString.add("이 식물은 "+data.get(key)+"입니다.");
            }
            if (data.containsKey(key)){
                resString.add(target + ": " + data.get(key));
            }
        }

        return resString;
    }

    // 건강 종류 정리해서 메시지 용으로 깔끔하게 전달(Map 형태)
    public Map<String, String> orgPlantHealthMap(String name, PlantPhotoHealthResDto data){
        Map<String, String> resMap = new HashMap<>();

        Map<String, String> labelMap = new HashMap<>(){{
            put("plantName", "식물 명");
            put("is_plant", "식물 여부");
            put("is_healthy", "건강 여부");
            put("local_name", "이름");
            put("description", "설명");
            put("prevention", "예방 방법");
        }};

        // 내부 요소들 순서대로
        List<String> labelList = Arrays.asList(
                "plantName",
                "is_plant",
                "is_healthy",
                "local_name",
                "description",
                "prevention"
        );

        String target = name;
        Boolean targetBl = false;
        Integer cnt = 0; String label = labelList.get(cnt);

        // plantName
        resMap.put(labelMap.get(label), name);
        cnt++;

        // isPlant
        label = labelList.get(cnt);
        targetBl = data.getIs_plant();
        if (targetBl){
            resMap.put(labelMap.get(label), "식물입니다.");
        } else {
            resMap.put(labelMap.get(label), "식물이 아닙니다.");
            return resMap;
        }
        cnt++;

        // is_healthy
        label = labelList.get(cnt);
        targetBl = data.getHealth_assessment().getIs_healthy();
        if (targetBl){
            resMap.put(labelMap.get(label), "건강합니다.");
        } else {
            resMap.put(labelMap.get(label), "건강에 이상이 있습니다.");
        }
        cnt++;

        // disease Details
        List<PlantPhotoHealthResDto.Diseases> dDs = data.getHealth_assessment().getDiseases();
        if (dDs != null && dDs.size() > 0) {
            PlantPhotoHealthResDto.Diseases.Disease_details dDt = dDs.get(0).getDisease_details();
            if (dDs.get(0).isRedundant()){
                dDt = dDs.get(1).getDisease_details();
            }

            // local_name
            label = labelList.get(cnt);
            target = dDt.getLocal_name();
            resMap.put(labelMap.get(label), target);
            cnt++;

            // description
            label = labelList.get(cnt);
            target = dDt.getDescription();
            resMap.put(labelMap.get(label), target);
            cnt++;

            // prevention
            label = labelList.get(cnt);
            List<String> pvList = dDt.getTreatment().getPrevention();
            if (pvList != null && pvList.size() > 0) {
                target = pvList.get(0);
                resMap.put(labelMap.get(label), target);
                cnt++;
            }
        }

        return resMap;
    }

    // 건강 종류 정리해서 메시지 용으로 깔끔하게 전달(Map 형태) / V3
    public Map<String, String> orgPlantHealthMapV3(String name, PlantPhotoHealthResV3Dto data){
        Map<String, String> resMap = new HashMap<>();

        Map<String, String> labelMap = new HashMap<>(){{
            put("plantName", "식물 명");
            put("is_plant", "식물 여부");
            put("is_healthy", "건강 여부");
            put("local_name", "이름");
            put("description", "설명");
            put("prevention", "예방 방법");
        }};

        // 내부 요소들 순서대로
        List<String> labelList = Arrays.asList(
                "plantName",
                "is_plant",
                "is_healthy",
                "local_name",
                "description",
                "prevention"
        );

        String target = name;
        Boolean targetBl = false;
        Integer cnt = 0; String label = labelList.get(cnt);

        // plantName
        resMap.put(labelMap.get(label), name);
        cnt++;

        // isPlant
        label = labelList.get(cnt);
        targetBl = data.getResult().getIs_plant().getBinary();
        if (targetBl){
            resMap.put(labelMap.get(label), "식물입니다.");
        } else {
            resMap.put(labelMap.get(label), "식물이 아닙니다.");
            return resMap;
        }
        cnt++;

        // is_healthy
        label = labelList.get(cnt);
        targetBl = data.getResult().getIs_healthy().getBinary();
        if (targetBl){
            resMap.put(labelMap.get(label), "건강합니다.");
        } else {
            resMap.put(labelMap.get(label), "건강에 이상이 있습니다.");
        }
        cnt++;

        // disease Details
        List<PlantPhotoHealthResV3Dto.Result.Disease.Suggestions> dDs = data.getResult().getDisease().getSuggestions();
        if (dDs != null && dDs.size() > 0) {
            PlantPhotoHealthResV3Dto.Result.Disease.Suggestions dDt = dDs.get(0);

            // local_name
            label = labelList.get(cnt);
            target = dDt.getDetails().getLocal_name();
            resMap.put(labelMap.get(label), target);
            cnt++;

            // description
            label = labelList.get(cnt);
            target = dDt.getDetails().getDescription();
            resMap.put(labelMap.get(label), target);
            cnt++;

            // prevention
            label = labelList.get(cnt);
            List<String> pvList = dDt.getDetails().getTreatment().getPrevention();
            if (pvList != null && pvList.size() > 0) {
                target = pvList.get(0);
                resMap.put(labelMap.get(label), target);
                cnt++;
            }
        }

        return resMap;
    }

}
