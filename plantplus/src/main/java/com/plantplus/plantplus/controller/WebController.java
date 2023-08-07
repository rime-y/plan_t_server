package com.plantplus.plantplus.controller;

import com.plantplus.plantplus.dto.TestDto;
import com.plantplus.plantplus.service.WebClientService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/web")
@Slf4j
public class WebController {
    @Autowired
    private WebClientService webClientService;

    @RequestMapping("/get")
    public String test(){
        String res = webClientService.getTest().toString();
        log.info("/api/v1/web/get  ok");
        return res;
    }

    @RequestMapping("/post")
    public String testPost(){
        ResponseEntity<TestDto> res = webClientService.postTestWithParamAndBody();

        return res.toString();
    }
}
