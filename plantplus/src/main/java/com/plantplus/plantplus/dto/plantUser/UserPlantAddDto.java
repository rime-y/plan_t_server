package com.plantplus.plantplus.dto.plantUser;

public class UserPlantAddDto {
    private String userId;
    private String id;
    private String name;
    private String memo;

    public UserPlantAddDto(){
    }

    public UserPlantAddDto(String userId, String id, String name, String memo){
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.memo = memo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
