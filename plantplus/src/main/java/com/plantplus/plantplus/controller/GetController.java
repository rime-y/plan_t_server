package com.plantplus.plantplus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
    // test get (매개변수 없음)
    @GetMapping(value = "/getNoV")
    public String getNoV(){
        return "getNoVariable";
    }
}
