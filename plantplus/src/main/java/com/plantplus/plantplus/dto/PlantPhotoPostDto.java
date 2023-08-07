package com.plantplus.plantplus.dto;


public class PlantPhotoPostDto {
// 이 서버에 post 보낼 때 쓰는 구조
    private String images;
    private String latitude;
    private String longitude;
    private String plant_details;

    public String getImages(){
        return images;
    }

    public void setImages(String images){
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
}
