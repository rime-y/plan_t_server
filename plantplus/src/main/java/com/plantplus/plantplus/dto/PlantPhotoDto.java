package com.plantplus.plantplus.dto;

import java.util.ArrayList;
import java.util.List;

public class PlantPhotoDto {
// plant id 서버에 보낼 때
    private List<String> images;
    private String latitude;
    private String longitude;
    private String plant_details;

    // 건강 판별 시
    private List<String> disease_details;

    public List<String> getImages(){
        return images;
    }

    public void setImages(List<String> images){
        this.images = images;
    }
    public String getLatitude(){
        return latitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    public String getPlant_details(){
        return plant_details;
    }

    public void setPlant_details(String plant_details){
        this.plant_details = plant_details;
    }

    public List<String> getDisease_details() {
        return disease_details;
    }

    public void setDisease_details(List<String> disease_details) {
        this.disease_details = disease_details;
    }
}
