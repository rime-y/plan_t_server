package com.plantplus.plantplus.dto;


import java.util.List;

public class PlantPhotoResTestDto {
    // 서버 받아온 정보(건강 확인)
    // 테스트 용

    private Health_assessment health_assessment;

    public Health_assessment getHealth_assessments() {
        return health_assessment;
    }

    public void setHealth_assessments(Health_assessment health_assessments) {
        this.health_assessment = health_assessments;
    }

    public static class Health_assessment {

        private boolean is_healthy;



        public boolean getisIs_healthy() {
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
        //private boolean redundant;

        private List<Disease_details> disease_details;

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

        // public boolean isRedundant() {
        //     return redundant;
        // }

        public Integer getProbability() {
            return probability;
        }

        //public void setRedundant(boolean redundant) {
        //     this.redundant = redundant;
        //  }

        public void setProbability(Integer probability) {
            this.probability = probability;
        }

        public List<Disease_details> getDisease_details() {
            return disease_details;
        }

        public void setDisease_details(List<Disease_details> disease_details) {
            this.disease_details = disease_details;
        }
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

/*
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
        }*/
    }

}




