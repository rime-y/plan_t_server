package com.plantplus.plantplus.dto;

import java.util.List;

public class PlantIdDto {
    private String id;
    private List<Integer> ids;
    private String plant_details;
    private String plant_language;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlant_language() {
        return plant_language;
    }

    public void setPlant_language(String plant_language) {
        this.plant_language = plant_language;
    }

    public String getPlant_details() {
        return plant_details;
    }

    public void setPlant_details(String plant_details) {
        this.plant_details = plant_details;
    }
}
