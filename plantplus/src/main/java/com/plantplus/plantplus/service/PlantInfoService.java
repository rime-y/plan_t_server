package com.plantplus.plantplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class PlantInfoService {
    @Autowired
    private WebClientService webClientService;

    private String plantNameKor;

    private String plantNameEng = "rose";
    private String plantNum;

    private Boolean isSearched;


    private Map<String, String> plantInfo;

    public PlantInfoService(String plantName, WebClientService webClientService){
        this.webClientService = webClientService;
        setPlantNameEng(plantName);

        calPlantNameKor();
        calPlantInfoList();
    }
    private void calPlantNameKor(){
        ResponseEntity<String> res = webClientService.getPlantSearch(plantNameEng);

        DomService domService = new DomService();
        domService.readDom(res.getBody());

        List<Map<String, String>> nodeList = domService.getDomNodeList();

        if (nodeList.size() > 0){
            // 가장 첫번째 후보를 지정한다
            setPlantNameKor(nodeList.get(0).get("cntntsSj")); // 이름
            setPlantNum(nodeList.get(0).get("cntntsNo")); // 컨텐츠 번호
            setSearched(Boolean.TRUE);
        } else {
            setSearched(Boolean.FALSE);
        }
    }

    private void calPlantInfoList(){
        ResponseEntity<String> res = webClientService.getPlantInfo(plantNum);

        DomService domService = new DomService();
        domService.readDom(res.getBody());

        List<Map<String, String>> nodeList = domService.getDomNodeList();

        if (nodeList.size() > 0){
            // 가장 첫번째 후보를 지정한다
            setPlantInfo(nodeList.get(0));
            System.out.println("test: "+nodeList.get(0).get("fmlNm"));
            setSearched(Boolean.TRUE);
        } else {
            setSearched(Boolean.FALSE);
        }
    }


    public void setPlantNameKor(String plantNameKor) {
        this.plantNameKor = plantNameKor;
    }

    public String getPlantNameKor() {
        return plantNameKor;
    }

    public String getPlantNameEng() {
        return plantNameEng;
    }

    public void setPlantNameEng(String plantNameEng) {
        this.plantNameEng = plantNameEng;
    }

    public void setPlantInfo(Map<String, String> plantInfo) {
        this.plantInfo = plantInfo;
    }

    public Map<String, String> getPlantInfo() {
        return plantInfo;
    }

    public String getPlantNum() {
        return plantNum;
    }

    public void setPlantNum(String plantNum) {
        this.plantNum = plantNum;
    }


    public Boolean getSearched() {
        return isSearched;
    }

    public void setSearched(Boolean searched) {
        isSearched = searched;
    }
}
