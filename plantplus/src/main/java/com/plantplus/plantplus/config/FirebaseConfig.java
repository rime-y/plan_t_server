package com.plantplus.plantplus.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {


    @Autowired
    ResourceLoader resourceLoader;
    @PostConstruct
    public void init(){
        try{
            // resources 위치 문제로(maven package했을 때) 코드 수정
            /*
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/serviceAccountKey.json");

             */


            // 2차 시도 실패
            /*
            ClassPathResource resource = new ClassPathResource("serviceAccountKey.json");
            FileInputStream serviceAccount =
                    new FileInputStream(resource.getPath());

            */



            // ResourceLoader 사용
            Resource resource = resourceLoader.getResource("classpath:serviceAccountKey.json");
            InputStream inputStream = resource.getInputStream();


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://planplus-3cf7d-default-rtdb.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
