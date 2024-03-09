package com.plantplus.plantplus.service;

import com.plantplus.plantplus.dto.*;
import com.plantplus.plantplus.dto.plantPhoto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class WebClientService {

    private final String plantIdApiKey = "vKjSKyM7qTmoI7rWMeuu0IIF0pzfZ88ir6brWM60nqLGuzQobD";
    private final String nongsaroApiKey = "20230621EUCNSZX5U0MEDLFWQCVFA";

    public String getTest() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public ResponseEntity<TestDto> postTestWithParamAndBody() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        TestDto testDto = new TestDto();
        testDto.setTitle("TestGood");
        testDto.setBody("Leanne Graham");
        testDto.setUserId("now");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("title", "TestGood2")
                .queryParam("body", "Leanne Graham2")
                .queryParam("userId", "now2")
                .build())
                .bodyValue(testDto)
                .retrieve()
                .toEntity(TestDto.class)
                .block();
    }

    public ResponseEntity<TestDto> postTestWithHeader() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        TestDto testDto = new TestDto();
        testDto.setTitle("TestGood");
        testDto.setBody("Leanne Graham");
        testDto.setUserId("now");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/users")
                        .build())
                .bodyValue(testDto)
                .header("my-header", "Wikibooks API")
                .retrieve()
                .toEntity(TestDto.class)
                .block();
    }

    public ResponseEntity<PlantIdDto> postPlantIdByID() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.plant.id/v2/get_identification_result")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();



        PlantIdDto plantIdDto = new PlantIdDto();
        plantIdDto.setId("83910980");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/ID")
                        .build())
                .bodyValue(plantIdDto)
                .header("Api-Key", plantIdApiKey)
                .retrieve()
                .toEntity(PlantIdDto.class)
                .block();
    }

    public ResponseEntity<PlantIdDto> postPlantIdByIDMulti() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.plant.id/v2/get_identification_result")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        PlantIdDto plantIdDto = new PlantIdDto();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(83910980);
        plantIdDto.setIds(ids);

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/multiple")
                        .build())
                .bodyValue(plantIdDto)
                .header("Api-Key", plantIdApiKey)
                .retrieve()
                .toEntity(PlantIdDto.class)
                .block();
    }

    public ResponseEntity<PlantIdDto> postPlantIdTest() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.plant.id/v2")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();



        PlantIdDto plantIdDto = new PlantIdDto();

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/usage_info")
                        .build())
                .bodyValue(plantIdDto)
                .header("Api-Key", plantIdApiKey)
                .retrieve()
                .toEntity(PlantIdDto.class)
                .block();
    }

    public ResponseEntity<PlantPhotoResDto> postPlantImage(List<String> img) {
        // 이미지 판별
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.plant.id/v2")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();


        PlantPhotoDto plantPhotoDto = new PlantPhotoDto();
        plantPhotoDto.setImages(img);
        log.info("size: ", plantPhotoDto.getImages().size());
        //log.info(plantPhotoDto.getImages().toString());

        ResponseEntity<PlantPhotoResDto> res = null;

        try {
            res = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/identify")
                            .build())
                    .bodyValue(plantPhotoDto)
                    .header("Api-Key", plantIdApiKey)
                    .retrieve()
                    .toEntity(PlantPhotoResDto.class)
                    .block();
        } catch (WebClientResponseException.BadRequest ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Bad Request Error: " + responseBody);
            log.info("Bad Request Error: " + responseBody);
            // Handle the specific error based on the response body or other information
        } catch (WebClientResponseException ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Other WebClientResponseException: " + responseBody);
            // Handle other WebClientResponseExceptions, if needed
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage() + ex.getCause());
            // Handle other exceptions, if needed
        }



        return res;
    }

    public ResponseEntity<PlantPhotoHealthResDto> postPlantHealthImage(List<String> img) {
        // 건강 판별
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.plant.id/v2")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        List<String> defaultDetailSettings = new ArrayList<>();

        defaultDetailSettings.add("language");
        defaultDetailSettings.add("description");
        defaultDetailSettings.add("local_name");
        defaultDetailSettings.add("treatment");
        defaultDetailSettings.add("url");

        PlantPhotoDto plantPhotoDto = new PlantPhotoDto();
        plantPhotoDto.setImages(img);
        plantPhotoDto.setLanguage("ko");
        plantPhotoDto.setDisease_details(defaultDetailSettings);
        // log.info(plantPhotoDto.getImages().toString());

        ResponseEntity<PlantPhotoHealthResDto> res = null;

        try {
            res = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/health_assessment")
                            .build())
                    .bodyValue(plantPhotoDto)
                    .header("Api-Key", plantIdApiKey)
                    .retrieve()
                    .toEntity(PlantPhotoHealthResDto.class)
                    .block();
        } catch (WebClientResponseException.BadRequest ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Bad Request Error: " + responseBody);
            log.info("Bad Request Error: " + responseBody);
            // Handle the specific error based on the response body or other information
        } catch (WebClientResponseException ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Other WebClientResponseException: " + responseBody);
            // Handle other WebClientResponseExceptions, if needed
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage()+ex.getCause());

            // Handle other exceptions, if needed
        }
        System.out.println("detail language "+res.getBody().getHealth_assessment().getDiseases().get(0).getDisease_details().getLanguage());
        return res;
    }

    public ResponseEntity<PlantPhotoHealthResV3Dto> postPlantHealthImageV3(List<String> img) {
        // 건강 판별 - 한국어로 받아오기 위한 v3 버전
        WebClient webClient = WebClient.builder()
                .baseUrl("https://plant.id/api/v3")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();


        PlantPhotoV3ReqDto plantPhotoDto = new PlantPhotoV3ReqDto();
        plantPhotoDto.setImages(img);

        ResponseEntity<PlantPhotoHealthResV3Dto> res = null;

        try {
            res = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/health_assessment")
                            .queryParam("language", "ko")
                            .queryParam("details", "local_name,description,url,treatment,classification,common_names,cause")
                            .build())
                    .bodyValue(plantPhotoDto)
                    .header("Api-Key", plantIdApiKey)
                    .retrieve()
                    .toEntity(PlantPhotoHealthResV3Dto.class)
                    .block();
        } catch (WebClientResponseException.BadRequest ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("2Bad Request Error: " + responseBody);
            log.info("Bad Request Error: " + responseBody);
            // Handle the specific error based on the response body or other information
        } catch (WebClientResponseException ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Other WebClientResponseException: " + responseBody);
            // Handle other WebClientResponseExceptions, if needed
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage()+ex.getCause());

            // Handle other exceptions, if needed
        }
        System.out.println("detail language "+res.getBody().getResult().getDisease().getSuggestions().get(0).getDetails().getLocal_name());
        return res;
    }


    public ResponseEntity<String> getPlantSearch(String plantName, String lang) {
        // 농사로 실내정원용 식물 정보 검색
        WebClient webClient = WebClient.builder()
                .baseUrl("http://api.nongsaro.go.kr/service/garden")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .defaultHeaders(headers -> {
                    headers.set("Accept-Charset", "UTF-8");
                    headers.set("apiKey", nongsaroApiKey);
                })
                .build();

        String st = "sPlntbneNm";
        if (lang == "ko") st = "sCntntsSj";
        else if (lang == "en") st = "sPlntzrNm";
        final String sType = st;


        ResponseEntity<String> res = null;


        try {
            res = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/gardenList")
                            .queryParam("apiKey", nongsaroApiKey)
                            .queryParam("sType", sType)  // 영명
                            .queryParam("sText", plantName)
                            .build())
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        } catch (WebClientResponseException.BadRequest ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Bad Request Error: " + responseBody);
            log.info("Bad Request Error: " + responseBody);
            // Handle the specific error based on the response body or other information
        } catch (WebClientResponseException ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Other WebClientResponseException: " + responseBody);
            // Handle other WebClientResponseExceptions, if needed
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
            // Handle other exceptions, if needed
        }

        return res;
    }

    public ResponseEntity<String> getPlantInfo(String plantNum) {
        // 농사로 실내정원용 식물 정보 검색 (세부정보)
        WebClient webClient = WebClient.builder()
                .baseUrl("http://api.nongsaro.go.kr/service/garden")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .defaultHeaders(headers -> {
                    headers.set("Accept-Charset", "UTF-8");
                    headers.set("apiKey", nongsaroApiKey);
                })
                .build();


        ResponseEntity<String> res = null;

        try {
            res = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/gardenDtl")
                            .queryParam("apiKey", nongsaroApiKey)
                            .queryParam("cntntsNo", plantNum)
                            .build())
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        } catch (WebClientResponseException.BadRequest ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Bad Request Error: " + responseBody);
            log.info("Bad Request Error: " + responseBody);
            // Handle the specific error based on the response body or other information
        } catch (WebClientResponseException ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("Other WebClientResponseException: " + responseBody);
            // Handle other WebClientResponseExceptions, if needed
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
            // Handle other exceptions, if needed
        }

        return res;
    }
}
