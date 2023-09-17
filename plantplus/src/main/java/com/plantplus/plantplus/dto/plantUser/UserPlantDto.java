package com.plantplus.plantplus.dto.plantUser;

public class UserPlantDto {
    private String id;
    private String name;
    private String memo;
    private String date;

    public UserPlantDto(){
        this.id = "";
        this.name = "";
        this.memo = "";
        this.date = "";
    }

    public UserPlantDto(String id, String name, String memo, String date){
        this.id = id;
        this.name = name;
        this.memo = memo;
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
