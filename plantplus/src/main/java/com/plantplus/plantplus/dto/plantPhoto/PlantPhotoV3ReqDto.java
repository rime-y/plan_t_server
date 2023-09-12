package com.plantplus.plantplus.dto.plantPhoto;

import java.util.List;


public class PlantPhotoV3ReqDto {
    // plant id 서버에 보낼 때 (v3)
    private List<String> images;

    public List<String> getImages(){
        return images;
    }

    public void setImages(List<String> images){
        this.images = images;
    }

}
