package com.plantplus.plantplus.dto.plantUser;

public class UserDto {
    private String id;
    private String full_name;
    public UserDto() {
        // 이게 없어서 해당 오류 발생
        // Class com.plantplus.plantplus.dto.myPlant.UserDto is missing a constructor with no arguments
    }
    public UserDto(String id, String full_name){
        this.id = id;
        this.full_name = full_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }




}
