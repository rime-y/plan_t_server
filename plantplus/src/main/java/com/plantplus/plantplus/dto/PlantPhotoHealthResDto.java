package com.plantplus.plantplus.dto;

import java.util.List;

public class PlantPhotoHealthResDto {
    // 서버 받아온 정보(건강 확인)
    private static List<PlantImages> images;
    private Integer id;
    private String custom_id;
    private String latitude;
    private String longitude;
    private String plant_details;

    private Health_assessment health_assessment;

    private String secret;
    private String fail_cause;
    private Boolean countable;
    private String feedback;
    private Integer is_plant_probability;
    private Boolean is_plant;

    private List<String> modifiers;
    private Meta_data meta_data;

    public void setMeta_data(Meta_data meta_data) {
        this.meta_data = meta_data;
    }

    public Meta_data getMeta_data() {
        return meta_data;
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


    public Health_assessment getHealth_assessment() {
        return health_assessment;
    }

    public void setHealth_assessment(Health_assessment health_assessment) {
        this.health_assessment = health_assessment;
    }

    public List<PlantImages> getImages(){
        return images;
    }

    public void setImages(List<PlantImages> images){
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

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public List<String> getModifiers() {
        return modifiers;
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

    public static class Health_assessment {

        private boolean is_healthy;



        public boolean getIs_healthy() {
            return is_healthy;
        }
        public void setIs_healthy(boolean is_healthy) {
            this.is_healthy = is_healthy;
        }
        private Integer is_healthy_probability;
        public Integer getIs_healthy_probability() {
            return is_healthy_probability;
        }
        public void setIs_healthy_probability(Integer is_healthy_probability) {
            this.is_healthy_probability = is_healthy_probability;
        }
        private List<Diseases> diseases;

        public List<Diseases> getDiseases() {
            return diseases;
        }

        public void setDiseases(List<Diseases> diseases) {
            this.diseases = diseases;
        }
    }
    public static class Diseases {
        private Integer entity_id;
        private String name;
        //private List<SimilarImages> similar_images;
        private Integer probability;
        private boolean redundant;

        private Disease_details disease_details;

        public void setEntity_id(Integer entity_id) {
            this.entity_id = entity_id;
        }

        public Integer getEntity_id() {
            return entity_id;
        }

       // public void setSimilar_images(List<SimilarImages> similar_images) {
        //    this.similar_images = similar_images;
       // }

        //public List<SimilarImages> getSimilar_images() {
        //    return similar_images;
       // }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isRedundant() {
            return redundant;
        }

        public Integer getProbability() {
            return probability;
        }

       public void setRedundant(boolean redundant) {
           this.redundant = redundant;
       }

        public void setProbability(Integer probability) {
            this.probability = probability;
        }

        public Disease_details getDisease_details() {
            return disease_details;
        }

        public void setDisease_details(Disease_details disease_details) {
            this.disease_details = disease_details;
        }
        public static class Disease_details {

            private String local_name;
            private String language;
            public void setLanguage(String language) {
                this.language = language;
            }

            public String getLanguage() {
                return language;
            }

            public String getLocal_name() {
                return local_name;
            }
            public void setLocal_name(String local_name) {
                this.local_name = local_name;
            }


        private Treatment treatment;
        private String cause;
        private String scientific_name;
        private List<String> common_names;
        private String description;
        private String url;

        public String getCause() {
            return cause;
        }

        public String getScientific_name() {
            return scientific_name;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public void setScientific_name(String scientific_name) {
            this.scientific_name = scientific_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        public List<String> getCommon_names() {
            return common_names;
        }

        public String getDescription() {
            return description;
        }


        public Treatment getTreatment() {
            return treatment;
        }

        public void setCommon_names(List<String> common_names) {
            this.common_names = common_names;
        }

        public void setDescription(String description) {
            this.description = description;
        }


        public void setTreatment(Treatment treatment) {
            this.treatment = treatment;
        }

        public static class Treatment {
            private List<String> prevention;

            public List<String> getPrevention() {
                return prevention;
            }

            public void setPrevention(List<String> prevention) {
                this.prevention = prevention;
            }
        }
        }
    }

    public static class SimilarImages {
        private String id;
        private String similarity;
        private String url;
        private String url_small;


        public String getId() {
            return id;
        }

        public String getSimilarity() {
            return similarity;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setSimilarity(String similarity) {
            this.similarity = similarity;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public String getUrl_small() {
            return url_small;
        }

        public void setUrl_small(String url_small) {
            this.url_small = url_small;
        }
    }



    /*
    public static class DiseaseDetails2 {
        private String cause;
        private String scientific_name;
        private List<String> common_names;
        private String description;
        private String url;
        private String local_name;
        private String language;
        private Treatment treatment;

        public String getCause() {
            return cause;
        }

        public String getScientific_name() {
            return scientific_name;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public void setScientific_name(String scientific_name) {
            this.scientific_name = scientific_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLanguage() {
            return language;
        }

        public List<String> getCommon_names() {
            return common_names;
        }

        public String getDescription() {
            return description;
        }

        public String getLocal_name() {
            return local_name;
        }

        public Treatment getTreatment() {
            return treatment;
        }

        public void setCommon_names(List<String> common_names) {
            this.common_names = common_names;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setLocal_name(String local_name) {
            this.local_name = local_name;
        }

        public void setTreatment(Treatment treatment) {
            this.treatment = treatment;
        }

        public static class Treatment {
            private List<String> prevention;

            public List<String> getPrevention() {
                return prevention;
            }

            public void setPrevention(List<String> prevention) {
                this.prevention = prevention;
            }
        }
    }
     */
    public static class Meta_data {
        private String latitude;
        private String longitude;
        private String date;
        private String datetime;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDatetime() {
            return datetime;
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
}



