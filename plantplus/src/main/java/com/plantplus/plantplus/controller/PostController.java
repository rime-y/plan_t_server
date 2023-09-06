package com.plantplus.plantplus.controller;

import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoHealthResDto;
import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoPostDto;
import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoResDto;
import com.plantplus.plantplus.service.WebClientService;
import com.plantplus.plantplus.service.PlantService;
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
@RequestMapping("/api/v1/post-api")
@Slf4j
public class PostController {
    @Autowired
    private WebClientService webClientService;

    private PlantService plantService = new PlantService();


    @PostMapping(value="/test")
    public String test(@RequestBody PlantPhotoPostDto plantPhotoPostDto){
        log.info("asdfasdf");
        log.info("plantPhotoPostDto", plantPhotoPostDto.getImages().toString());
        return plantPhotoPostDto.toString();
    }

    // http://localhost:8080/api/v1/post-api/plantPhoto
    @PostMapping(value="/plantPhoto")
    public String postPlant(@RequestBody PlantPhotoPostDto plantPhotoDto){
        // 사진 업로드 (종류 판별)
        List<String> images = new ArrayList<>();
        images.add(plantPhotoDto.getImages());
        log.error("test");
        log.info("plantPhotoPostDto", plantPhotoDto.getImages().length());

        ResponseEntity<PlantPhotoResDto> res = webClientService.postPlantImage(images);
        if (res == null){
            return "null";
        }
        return res.getBody().getId().toString();
        //return plantPhotoDto.toString();
    }

    // http://localhost:8080/api/v1/post-api/plantPhotoHealth
    @PostMapping(value="/plantPhotoHealth")
    public String postPlantHealth(@RequestBody PlantPhotoPostDto plantPhotoDto){
        // 사진 업로드 (건강 판별)
        List<String> images = new ArrayList<>();
        String resString = "";
        images.add(plantPhotoDto.getImages());
        log.error("test");
        log.info("plantPhotoPostDto", images, plantPhotoDto.getImages().toString());

        ResponseEntity<PlantPhotoHealthResDto> res = webClientService.postPlantHealthImage(images);
       // ResponseEntity<PlantPhotoResTestDto> res = webClientService.postPlantHealthImage(images);

        if (res != null){
            System.out.println("check: "+res.getBody().getId().toString());
            System.out.println("check 2: "+res.getBody().getImages().get(0).getFile_name());
            resString = res.getBody().getHealth_assessment().getDiseases().get(0).getDisease_details().getUrl().toString();
        }
        return resString;
        //return plantPhotoDto.toString();
    }

    // http://localhost:8080/api/v1/post-api/plantPhotoAndInfo
    @PostMapping(value="/plantPhoto/Info")
    public Map<String, String> postPlantPhotoWithInfo(@RequestBody PlantPhotoPostDto plantPhotoDto){
        // 사진 업로드 (종류 판별 및 정보)
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
            System.out.println("plantRes: "+plantRes);
            plantInfoMap = plantService.getPlantInfo(plantRes, webClientService, "sc");
            //log.info("plantInfoRes: ", plantInfoMap.get("plntbneNm"));
        } else {
            log.info("plantRes null");
        }

        if (plantInfoMap != null){
            plantInfoResMap = plantService.orgPlantInfoMap(plantInfoMap);
            System.out.println("plantInfoResMap "+plantInfoResMap.get("plantName"));
            List<String> plantInfoResStr = plantService.orgPlantInfoString(plantInfoMap);
            plantInfoResMap.put("설명", plantInfoResStr.get(0));
        } else {
            System.out.println("plantInfoResMap Empty");
        }


        return plantInfoResMap;
        //return plantPhotoDto.toString();
    }
}
