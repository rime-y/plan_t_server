package com.plantplus.plantplus.controller;

import com.plantplus.plantplus.dto.PlantPhotoHealthResDto;
import com.plantplus.plantplus.dto.PlantPhotoPostDto;
import com.plantplus.plantplus.dto.PlantPhotoResDto;
import com.plantplus.plantplus.service.WebClientService;
import com.plantplus.plantplus.service.PlantService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        String plantInfo = "";
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
            plantInfoMap = plantService.getPlantInfo(plantRes, webClientService);
        } else {
            log.info("plantRes null");
        }

        if (!plantInfoMap.isEmpty()){
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
        String resString = "";
        images.add(plantPhotoDto.getImages());
        log.error("test");
        log.info("plantPhotoPostDto", images, plantPhotoDto.getImages().toString());

        ResponseEntity<PlantPhotoHealthResDto> res = webClientService.postPlantHealthImage(images); // 건강
        ResponseEntity<PlantPhotoResDto> resName = webClientService.postPlantImage(images); // 이름

        // 첫번째 suggestion의 결과를 택한다
        List<PlantPhotoResDto.PlantSuggestions> plantSuggestionsList = resName.getBody().getSuggestions();
        String plantRes = "";
        String plantInfo = "";
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
            plantInfoMap = plantService.getPlantInfo(plantRes, webClientService);
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


}
