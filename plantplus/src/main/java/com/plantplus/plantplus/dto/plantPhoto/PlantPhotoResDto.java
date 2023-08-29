package com.plantplus.plantplus.dto.plantPhoto;


import java.util.List;

public class PlantPhotoResDto {
// 서버 받아온 정보(종류 식별)
    private List<PlantImages> images;
    private Integer id;
    private MetaData meda_data;
    private String custom_id;

    private String uploaded_datetime;
    private String finished_datetime;

    private String plant_details;

    private List<PlantSuggestions> suggestions;

    private String secret;
    private String fail_cause;
    private Boolean countable;
    private String feedback;
    private Integer is_plant_probability;
    private Boolean is_plant;
    private List<String> modifiers;

    public String getUploaded_datetime() {
        return uploaded_datetime;
    }

    public String getFinished_datetime() {
        return finished_datetime;
    }

    public void setFinished_datetime(String finished_datetime) {
        this.finished_datetime = finished_datetime;
    }

    public void setUploaded_datetime(String uploaded_datetime) {
        this.uploaded_datetime = uploaded_datetime;
    }

    public MetaData getMeda_data() {
        return meda_data;
    }

    public void setMeda_data(MetaData meda_data) {
        this.meda_data = meda_data;
    }

    public Boolean getCountable() {
        return countable;
    }

    public Boolean getIs_plant() {
        return is_plant;
    }

    public Integer getIs_plant_probability() {
        return is_plant_probability;
    }

    public String getFail_cause() {
        return fail_cause;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getSecret() {
        return secret;
    }

    public void setCountable(Boolean countable) {
        this.countable = countable;
    }

    public void setFail_cause(String fail_cause) {
        this.fail_cause = fail_cause;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setIs_plant(Boolean is_plant) {
        this.is_plant = is_plant;
    }

    public void setIs_plant_probability(Integer is_plant_probability) {
        this.is_plant_probability = is_plant_probability;
    }

    public List<PlantSuggestions> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<PlantSuggestions> suggestions) {
        this.suggestions = suggestions;
    }

    public List<PlantImages> getImages(){
        return images;
    }

    public void setImages(List<PlantImages> images){
        this.images = images;
    }

    public String getPlant_details(){
        return plant_details;
    }

    public void setPlant_details(String plant_details){
        this.plant_details = plant_details;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    class MetaData {
        private String latitude;
        private String longitude;
        private String date;
        private String datetime;

        public String getDate() {
            return date;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
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
    }
    public static class PlantImages {
        private String file_name;
        private String url;

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_name() {
            return file_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class PlantSuggestions {
        private String id;
        private String plant_name;
        private PlantDetails plant_details;
        private Integer probability;
        private boolean confirmed;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setPlant_details(PlantDetails plant_details) {
            this.plant_details = plant_details;
        }

        public PlantDetails getPlant_details() {
            return plant_details;
        }

        public String getPlant_name() {
            return plant_name;
        }

        public void setPlant_name(String plant_name) {
            this.plant_name = plant_name;
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public Integer getProbability() {
            return probability;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public void setProbability(Integer probability) {
            this.probability = probability;
        }

    }

    public static class PlantDetails {
        private String language;
        private String scientific_name;


        public String getLanguage() {
            return language;
        }

        public String getScientific_name() {
            return scientific_name;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setScientific_name(String scientific_name) {
            this.scientific_name = scientific_name;
        }
    }

}

