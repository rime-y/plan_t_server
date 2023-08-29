package com.plantplus.plantplus.dto.plantUser;

public class UserPlantDto {
    private String id;
    private String name;
    private String memo;

    public UserPlantDto(){
        this.id = "";
        this.name = "";
        this.memo = "";
    }

    public UserPlantDto(String id, String name, String memo){
        this.id = id;
        this.name = name;
        this.memo = memo;
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
}
