package com.plantplus.plantplus.controller;

import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoHealthResV3Dto;
import com.plantplus.plantplus.dto.plantUser.UserDto;
import com.plantplus.plantplus.dto.plantUser.UserPlantAddDto;
import com.plantplus.plantplus.dto.plantUser.UserPlantDto;
import com.plantplus.plantplus.dto.plantEnv.PlantEnvPostDto;
import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoHealthResDto;
import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoPostDto;
import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoResDto;
import com.plantplus.plantplus.dto.plantSearch.PlantSearchDto;
import com.plantplus.plantplus.service.FirebaseService;
import com.plantplus.plantplus.service.WebClientService;
import com.plantplus.plantplus.service.PlantService;
import com.plantplus.plantplus.util.UtilClass;
import jdk.jshell.execution.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/plant-api")
@Slf4j
public class PlantController {
    @Autowired
    private WebClientService webClientService;

    private PlantService plantService = new PlantService();


    /**
     * 식물 종류 판별 및 세부 정보 제공
     * @param plantPhotoDto
     * @return
     */
    // http://localhost:8080/api/v1/plant-api/getPlantInfo
    @PostMapping(value="/getPlantInfo")
    public Map<String, String> getPlantInfo(@RequestBody PlantPhotoPostDto plantPhotoDto){
        // 사진을 업로드하면, 종류를 판별하고 관련 정보를 돌려줌
        List<String> images = new ArrayList<>();
        images.add(plantPhotoDto.getImages());
        plantService = new PlantService();

        ResponseEntity<PlantPhotoResDto> res = webClientService.postPlantImage(images);

        // 첫번째 suggestion의 결과를 택한다
        List<PlantPhotoResDto.PlantSuggestions> plantSuggestionsList = res.getBody().getSuggestions();
        String plantRes = "";
        Map<String, String> plantInfoMap = new HashMap<>();
        Map<String, String> plantInfoResMap = new HashMap<>();

        if (plantSuggestionsList.size() > 0){
            plantRes = plantSuggestionsList.get(0).getPlant_name();
            System.out.println(plantSuggestionsList.get(0).getPlant_name());
            log.info("plantSuggestionsList ",plantSuggestionsList.get(0).toString());
        } else {
            System.out.println("plantSuggestionsList 0 "+plantSuggestionsList.size());
            plantRes = null;
        }
        System.out.println("plantSuggestionsList 0 "+plantSuggestionsList.get(0).getId()+ " // "+res.getBody().getId());

        if (plantRes != null ){
            // 세부정보 받아오기
            System.out.println("plantRes: "+plantRes);
            plantInfoMap = plantService.getPlantInfo(plantRes, webClientService, "sc");
        } else {
            log.info("plantRes null");
        }

        if (plantInfoMap != null ){
            // 정리
            plantInfoResMap = plantService.orgPlantInfoMap(plantInfoMap);
            System.out.println("plantInfoResMap "+plantInfoResMap.get("plantName"));
            List<String> plantInfoResStr = plantService.orgPlantInfoString(plantInfoMap);
            plantInfoResMap.put("설명", plantInfoResStr.get(0));
        } else {
            System.out.println("plantInfoResMap Empty");
        }


        return plantInfoResMap;
    }

    // http://localhost:8080/api/v1/plant-api/getPlantHealth
    @PostMapping(value="/getPlantHealth")
    public Map<String, String> getPlantHealth(@RequestBody PlantPhotoPostDto plantPhotoDto){
        // 사진 업로드하면, 종류를 판별하고 건강 상태를 돌려줌
        List<String> images = new ArrayList<>();
        images.add(plantPhotoDto.getImages());
        log.error("test");
        log.info("plantPhotoPostDto", images, plantPhotoDto.getImages().toString());

        ResponseEntity<PlantPhotoHealthResDto> res = webClientService.postPlantHealthImage(images); // 건강
        ResponseEntity<PlantPhotoResDto> resName = webClientService.postPlantImage(images); // 이름

        // 첫번째 suggestion의 결과를 택한다
        List<PlantPhotoResDto.PlantSuggestions> plantSuggestionsList = resName.getBody().getSuggestions();
        String plantRes = "";
        Map<String, String> plantInfoMap = new HashMap<>();
        Map<String, String> plantInfoResMap = new HashMap<>();

        if (plantSuggestionsList.size() > 0){
            plantRes = plantSuggestionsList.get(0).getPlant_name();
            System.out.println(plantSuggestionsList.get(0).getPlant_name());
            log.info("plantSuggestionsList ",plantSuggestionsList.get(0).toString());
        } else {
            System.out.println("plantSuggestionsList 0 "+plantSuggestionsList.size());
            plantRes = null;
        }
        System.out.println("plantSuggestionsList 0 "+plantSuggestionsList.get(0).getId()+ " // "+resName.getBody().getId());

        if (plantRes != null){
            // 세부정보 받아오기
            System.out.println("plantRes: "+plantRes);
            plantInfoMap = plantService.getPlantInfo(plantRes, webClientService, "sc");
        } else {
            log.info("plantRes null");
        }

        if (plantInfoMap != null){
            // 정리
            plantInfoResMap = plantService.orgPlantHealthMap(plantInfoMap.get("plantName"), res.getBody());
            System.out.println("plantInfoResMap "+plantInfoResMap.get("plantName"));
        } else {
            System.out.println("plantInfoResMap Empty");
        }


        // 테스트 용 (string)
        if (res != null){
            System.out.println("check: "+res.getBody().getId().toString());
            System.out.println("check 2: "+res.getBody().getImages().get(0).getFile_name());
          //  resString = res.getBody().getHealth_assessment().getDiseases().get(0).getDisease_details().getUrl().toString();
        }


        return plantInfoResMap;
    }

    // http://localhost:8080/api/v1/plant-api/getPlantHealthV3
    @PostMapping(value="/getPlantHealthV3")
    public Map<String, String> getPlantHealthV3(@RequestBody PlantPhotoPostDto plantPhotoDto){
        // 사진 업로드하면, 종류를 판별하고 건강 상태를 돌려줌 (v3)
        List<String> images = new ArrayList<>();
        images.add(plantPhotoDto.getImages());
        log.error("test");
        log.info("plantPhotoPostDto", images, plantPhotoDto.getImages().toString());

        ResponseEntity<PlantPhotoHealthResV3Dto> res = webClientService.postPlantHealthImageV3(images); // 건강
        ResponseEntity<PlantPhotoResDto> resName = webClientService.postPlantImage(images); // 이름

        // 첫번째 suggestion의 결과를 택한다(이름)
        List<PlantPhotoResDto.PlantSuggestions> plantSuggestionsList = resName.getBody().getSuggestions();
        String plantRes = "";
        Map<String, String> plantInfoMap = new HashMap<>();
        Map<String, String> plantInfoResMap = new HashMap<>();

        if (plantSuggestionsList.size() > 0){
            plantRes = plantSuggestionsList.get(0).getPlant_name();
            System.out.println(plantSuggestionsList.get(0).getPlant_name());
            log.info("plantSuggestionsList ",plantSuggestionsList.get(0).toString());
        } else {
            System.out.println("plantSuggestionsList 0 "+plantSuggestionsList.size());
            plantRes = null;
        }
        System.out.println("plantSuggestionsList 0 "+plantSuggestionsList.get(0).getId()+ " // "+resName.getBody().getId());

        if (plantRes != null){
            // 세부정보 받아오기
            System.out.println("plantRes: "+plantRes);
            plantInfoMap = plantService.getPlantInfo(plantRes, webClientService, "sc");
        } else {
            log.info("plantRes null");
        }

        if (plantInfoMap != null){
            // 정리
            plantInfoResMap = plantService.orgPlantHealthMapV3(plantInfoMap.get("plantName"), res.getBody());
            System.out.println("plantInfoResMap "+plantInfoResMap.get("plantName"));
        } else {
            System.out.println("plantInfoResMap Empty");
        }


        // 테스트 용 (string)
        if (res != null){
            System.out.println("check: "+res.getBody().getStatus().toString());
            System.out.println("check 2: "+res.getBody().getInput().getImages().get(0));
            //  resString = res.getBody().getHealth_assessment().getDiseases().get(0).getDisease_details().getUrl().toString();
        }


        return plantInfoResMap;
    }

    /**
     * 환경 정보 분석 및 세부 정보 제공
     * @param plantEnvPostDto
     * @return
     */
    // http://localhost:8080/api/v1/plant-api/getEnvInfo
    @PostMapping(value="/getEnvInfo")
    public Map<String, String> getEnvInfo(@RequestBody PlantEnvPostDto plantEnvPostDto){
        // 조도 정보를 보내면, 분석하여 돌려줌

        Map<String, String> plantInfoResMap = new HashMap<>();

        return plantInfoResMap;
    }

    /**
     * 식물 검색 및 세부 정보 제공
     * @param plantSearchDto
     * @return
     */
    // http://localhost:8080/api/v1/plant-api/getPlantSearch
    @PostMapping(value="/getPlantSearch")
    public Map<String, String> getPlantSearch(@RequestBody PlantSearchDto plantSearchDto){

        String plantRes = plantSearchDto.getPlantName();
        Map<String, String> plantInfoMap = new HashMap<>();
        Map<String, String> plantInfoResMap = new HashMap<>();

        if (plantRes != null ){
            // 세부정보 받아오기
            System.out.println("plantRes: "+plantRes);
            plantInfoMap = plantService.getPlantInfo(plantRes, webClientService, "ko");
        } else {
            log.info("plantRes null");
        }

        if (plantInfoMap != null){
            // 정리
            plantInfoResMap = plantService.orgPlantInfoMap(plantInfoMap);
            System.out.println("plantInfoResMap "+plantInfoResMap.get("plantName"));
            // List<String> plantInfoResStr = plantService.orgPlantInfoString(plantInfoMap);
            // plantInfoResMap.put("설명", plantInfoResStr.get(0));
        } else {
            System.out.println("plantInfoResMap Empty");
        }


        return plantInfoResMap;
    }


    /**
     * 나의 식물 추가
     */
    // http://localhost:8080/api/v1/plant-api/createMyPlant
    @PostMapping(value="/createMyPlant")
    public Map<String, String> createMyPlant(@RequestBody UserPlantAddDto userPlantAddDto) {
        Map<String, String> resultMap = new HashMap<>();

        FirebaseService firebaseService = new FirebaseService();

        try {
            if (userPlantAddDto.getUserId() == null){
                resultMap.put("error", "no UserId");
                return resultMap;
            }
            if (userPlantAddDto.getId() == null){
                // 부여된 식물 id가 없으면 임의로 부여
                resultMap.put("error", "no plantId");
                userPlantAddDto.setId(Long.toString(System.currentTimeMillis()));
            }
            UserPlantDto userPlantDto = new UserPlantDto(userPlantAddDto.getId(), userPlantAddDto.getName(), userPlantAddDto.getMemo(), UtilClass.getStringDate());
            String res = firebaseService.insertPlant(userPlantAddDto.getUserId(), userPlantDto);
            resultMap.put("result", "success");
            resultMap.put("detail", res);
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return resultMap;
    }

    /**
     * 나의 식물 읽기 (유저 id가 주어졌을 때 목록)
     */
    // http://localhost:8080/api/v1/plant-api/readMyPlant
    @PostMapping(value="/readMyPlant")
    public  List<UserPlantDto> readMyPlant(@RequestBody UserDto userDto) {
        Map<String, String> resultMap = new HashMap<>();
        List<UserPlantDto> userPlantDtoList = new ArrayList<>();

        FirebaseService firebaseService = new FirebaseService();


        try {
            if (userDto.getId() == null){
                resultMap.put("error", "no userId");
                return userPlantDtoList;
            }
            userPlantDtoList = firebaseService.getPlantList(userDto.getId());
            return userPlantDtoList;
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return userPlantDtoList;
    }

    /**
     * 나의 식물 읽기 (유저 id와 memo id가 주어졌을 때 하나만)
     */
    // http://localhost:8080/api/v1/plant-api/readMyPlantDetail
    @PostMapping(value="/readMyPlantDetail")
    public  UserPlantDto readMyPlantDetail(@RequestBody UserPlantAddDto userPlantAddDto) {
        Map<String, String> resultMap = new HashMap<>();
        UserPlantDto userPlantDto = new UserPlantDto();

        FirebaseService firebaseService = new FirebaseService();


        try {
            if (userPlantAddDto.getId() == null){
                resultMap.put("error", "no userId");
                return userPlantDto;
            }
            userPlantDto = firebaseService.getPlantDetail(userPlantAddDto.getUserId(), userPlantAddDto.getId());
            return userPlantDto;
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return userPlantDto;
    }

    /**
     * 나의 식물 수정
     */
    // http://localhost:8080/api/v1/plant-api/updateMyPlant
    @PostMapping(value="/updateMyPlant")
    public Map<String, String> updateMyPlant(@RequestBody UserPlantAddDto userPlantAddDto) {
        Map<String, String> resultMap = new HashMap<>();

        FirebaseService firebaseService = new FirebaseService();

        try {
            if (userPlantAddDto.getUserId() == null){
                resultMap.put("error", "no UserId");
                return resultMap;
            }
            if (userPlantAddDto.getId() == null){
                // 부여된 식물 id가 없으면 임의로 부여
                resultMap.put("error", "no plantId");
            }
            UserPlantDto userPlantDto = new UserPlantDto(userPlantAddDto.getId(), userPlantAddDto.getName(), userPlantAddDto.getMemo(), UtilClass.getStringDate());
            String res = firebaseService.updatePlant(userPlantAddDto.getUserId(), userPlantDto);
            resultMap.put("result", "success");
            resultMap.put("detail", res);
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return resultMap;
    }

    /**
     * 나의 식물 제거
     */
    // http://localhost:8080/api/v1/plant-api/deleteMyPlant
    @PostMapping(value="/deleteMyPlant")
    public Map<String, String> deleteMyPlant(@RequestBody UserPlantAddDto userPlantAddDto) {
        Map<String, String> resultMap = new HashMap<>();

        FirebaseService firebaseService = new FirebaseService();

        try {
            if (userPlantAddDto.getUserId() == null){
                resultMap.put("error", "no UserId");
                return resultMap;
            }
            if (userPlantAddDto.getId() == null){
                // 부여된 식물 id가 없으면 임의로 부여
                resultMap.put("error", "no plantId");
            }
            String res = firebaseService.deletePlant(userPlantAddDto.getUserId(), userPlantAddDto.getId());
            resultMap.put("result", "success");
            resultMap.put("detail", res);
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return resultMap;
    }


}
