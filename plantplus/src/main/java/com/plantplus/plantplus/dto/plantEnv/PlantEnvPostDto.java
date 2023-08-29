package com.plantplus.plantplus.dto.plantEnv;

public class PlantEnvPostDto {
    private Float temp;
    private Float light;
    private Float locLat;
    private Float locLong;

    public Float getLight() {
        return light;
    }

    public Float getLocLat() {
        return locLat;
    }

    public Float getLocLong() {
        return locLong;
    }

    public Float getTemp() {
        return temp;
    }

    public void setLight(Float light) {
        this.light = light;
    }

    public void setLocLat(Float locLat) {
        this.locLat = locLat;
    }

    public void setLocLong(Float locLong) {
        this.locLong = locLong;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }
}
