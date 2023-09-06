package com.plantplus.plantplus.controller;

import com.plantplus.plantplus.dto.plantPhoto.PlantIdDto;
import com.plantplus.plantplus.dto.plantPhoto.PlantPhotoResDto;
import com.plantplus.plantplus.service.DomService;
import com.plantplus.plantplus.service.PlantInfoService;
import com.plantplus.plantplus.service.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class planController {
    @Autowired
    private WebClientService webClientService;


    @RequestMapping("/test")
    public String test(){
        return "hello World";
    }

    @RequestMapping("/postId")
    public String testPost(){
        ResponseEntity<PlantIdDto> res = webClientService.postPlantIdTest();

        return res.toString();
    }

    @RequestMapping("/postImage")
    public String ImagePost(){
        List<String> images = new ArrayList<>();

        //images[0] = image;
        //images.add(img);

       // ResponseEntity<PlantIdDto> res = webClientService.postPlantIdByIDMulti();
        ResponseEntity<PlantPhotoResDto> res = webClientService.postPlantImage(images);

        return res.toString();
    }

    @RequestMapping("/getPlantSearch")
    // http://localhost:8080/api/v1/getPlantSearch
    public String PlantSearch(){
        String plantName = "Philodendron";
        String result = "No Result";

        plantName = "rose";
        ResponseEntity<String> res = webClientService.getPlantSearch(plantName, "en");

        DomService domService = new DomService();
        domService.readDom(res.getBody());

        List<Map<String, String>> nodeList = domService.getDomNodeList();

        int size = nodeList.size();
        System.out.println("size: "+size);
        if (nodeList.size() > 0){
            result = "";
            for (int i = 0; i < nodeList.size(); i++){
                result += "(" + (i+1) +") "+nodeList.get(i).get("cntntsSj") + "\n";
            }
          //log.info("node: ", nodeList.get(1), "item: ", nodeList.get(1).get("cntntsSj"));
        }
        return result;
        //return res.getBody();
    }

    @GetMapping("/getPlantSearchTest")
    // http://localhost:8080/api/v1/getPlantSearchTest
    public String PlantSearchTest(@RequestParam String name){
        PlantInfoService plantInfoService = new PlantInfoService(name, webClientService, "en");
        String res = "";

        if (plantInfoService.getSearched() && (plantInfoService.getPlantNameKor() != null)){
            String info1 = plantInfoService.getPlantInfo().get("fmlCodeNm");
            res = "이름: "+plantInfoService.getPlantNameKor()+" 과명: "+info1;
        } else {
            res = "검색 결과 없음";
        }

        return res;
    }


}
