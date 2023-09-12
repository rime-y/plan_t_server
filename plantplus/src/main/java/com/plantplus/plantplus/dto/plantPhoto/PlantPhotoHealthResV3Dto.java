package com.plantplus.plantplus.dto.plantPhoto;

import java.util.List;

public class PlantPhotoHealthResV3Dto {
    // 서버 받아온 정보(건강 확인) - v3


    private String custom_id;
    private Input input;
    private Result result;
    private String status;

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public Input getInput() {
        return input;
    }

    public String getStatus() {
        return status;
    }

    public Result getResult() {
        return result;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Input {

        private String latitude;
        private String longitude;
        private List<String> images;

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class Result {

        private IsPlant is_plant;
        private IsHealthy is_healthy;
        private Disease disease;

        public void setIs_plant(IsPlant is_plant) {
            this.is_plant = is_plant;
        }

        public void setIs_healthy(IsHealthy is_healthy) {
            this.is_healthy = is_healthy;
        }

        public IsPlant getIs_plant() {
            return is_plant;
        }

        public Disease getDisease() {
            return disease;
        }

        public IsHealthy getIs_healthy() {
            return is_healthy;
        }

        public void setDisease(Disease disease) {
            this.disease = disease;
        }

        public static class IsPlant {
            private Float probability;
            private Boolean binary;
            private Float threshold;

            public void setProbability(Float probability) {
                this.probability = probability;
            }

            public Boolean getBinary() {
                return binary;
            }

            public Float getProbability() {
                return probability;
            }

            public Float getThreshold() {
                return threshold;
            }

            public void setBinary(Boolean binary) {
                this.binary = binary;
            }

            public void setThreshold(Float threshold) {
                this.threshold = threshold;
            }
        }

        public static class IsHealthy {
            private Float probability;
            private Boolean binary;
            private Float threshold;

            public void setProbability(Float probability) {
                this.probability = probability;
            }

            public Boolean getBinary() {
                return binary;
            }

            public Float getProbability() {
                return probability;
            }

            public Float getThreshold() {
                return threshold;
            }

            public void setBinary(Boolean binary) {
                this.binary = binary;
            }

            public void setThreshold(Float threshold) {
                this.threshold = threshold;
            }
        }

        public static class Disease {
            private List<Suggestions> suggestions;

            public void setSuggestions(List<Suggestions> suggestions) {
                this.suggestions = suggestions;
            }

            public List<Suggestions> getSuggestions() {
                return suggestions;
            }

            public static class Suggestions {
                private String id;
                private String name;
                private Float probability;

                private Details details;

                public Float getProbability() {
                    return probability;
                }

                public void setProbability(Float probability) {
                    this.probability = probability;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName() {
                    return name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Details getDetails() {
                    return details;
                }

                public void setDetails(Details details) {
                    this.details = details;
                }

                public static class Details {
                    private String local_name;
                    private String description;
                    private List<String> classification;
                    private String cause;
                    private String language;
                    private Treatment treatment;

                    public void setLanguage(String language) {
                        this.language = language;
                    }

                    public String getLanguage() {
                        return language;
                    }

                    public void setLocal_name(String local_name) {
                        this.local_name = local_name;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getLocal_name() {
                        return local_name;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public List<String> getClassification() {
                        return classification;
                    }

                    public String getCause() {
                        return cause;
                    }

                    public void setCause(String cause) {
                        this.cause = cause;
                    }

                    public void setClassification(List<String> classification) {
                        this.classification = classification;
                    }

                    public void setTreatment(Treatment treatment) {
                        this.treatment = treatment;
                    }

                    public Treatment getTreatment() {
                        return treatment;
                    }

                    public static class Treatment {
                        private List<String> biological;
                        private List<String> prevention;

                        public void setPrevention(List<String> prevention) {
                            this.prevention = prevention;
                        }

                        public List<String> getPrevention() {
                            return prevention;
                        }

                        public List<String> getBiological() {
                            return biological;
                        }

                        public void setBiological(List<String> biological) {
                            this.biological = biological;
                        }
                    }
                }
            }
        }

   }

}



