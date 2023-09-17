package com.plantplus.plantplus.controller;

import com.plantplus.plantplus.dto.TestDto;
import com.plantplus.plantplus.dto.plantUser.UserDto;
import com.plantplus.plantplus.dto.plantUser.UserPlantDto;
import com.plantplus.plantplus.service.FirebaseService;
import com.plantplus.plantplus.service.WebClientService;
import com.plantplus.plantplus.util.UtilClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/web")
@Slf4j
public class WebController {
    @Autowired
    private WebClientService webClientService;

    @RequestMapping("/get")
    public String testget(){
        String res = webClientService.getTest().toString();
        log.info("/api/v1/web/get  ok");
        return res;
    }

    @RequestMapping("/post")
    public String testPost(){
        ResponseEntity<TestDto> res = webClientService.postTestWithParamAndBody();

        return res.toString();
    }

    @GetMapping("/test")
    public String testgetFirebase(){
        FirebaseService firebaseService = new FirebaseService();
        //firebaseService.readUser();

        try {
            UserDto userDto = new UserDto();
            userDto.setId("t1234");
            userDto.setFull_name("Tilly Brown");
            UserPlantDto userPlantDto = new UserPlantDto("e", "rose", "it's my rose. pretty", UtilClass.getStringDate());
            //firebaseService.getUserDetail("Jenny");
            firebaseService.insertUser(userDto);
            firebaseService.insertPlant("Jenny", userPlantDto);
            firebaseService.getUserList();
            firebaseService.getPlantList("t1234");
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
        }

        return "ok";
    }
}
