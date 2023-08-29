package com.plantplus.plantplus.service;

import java.util.HashMap;
import java.util.Map;

public class TransService {
    // 안내 메시지 간략하게 번역

    private String findInMap(String msg, Map<String, String> transMap){
        // Map<s,s>에서 검색
        // map .get으로 일치하는 거 1차적
        // 전체 iterate 해서 부분 일치

        if (transMap.containsKey(msg)){
            System.out.println("exist "+transMap.get(msg));
            return transMap.get(msg);
        }
        else {
            // 없을 경우 map
            System.out.println("not "+msg);
            for (String key : transMap.keySet()){
                if (-1 < key.indexOf(msg)){
                    // 일부 일치할 경우 (가장 처음의 경우)
                    return transMap.get(key);
                }
            }
            return msg;
        }
    }
    private String healthNameTranslate(String msg){
        // 식물 건강 - 이름
        String res = "";
        Map<String, String> transMap = new HashMap<>();

        // 병명
        transMap.put("abiotic", "비생물적 장애");
        transMap.put("water-related issue", "물 관련 문제");
        transMap.put("water excess or uneven watering", "물 과다 또는 고르지 않은 물 공급");

        // general
        transMap.put("water", "물 관련 문제");

        return findInMap(msg, transMap);
    }

    private String healthDetTranslate(String msg){
        // 식물 건강 - 설명
        String res = "";
        Map<String, String> transMap = new HashMap<>();

        // 설명
        transMap.put("Abiotic disorders are caused by non-living factors - usually by unsuitable environmental conditions, such as drought stress, nutrient deficiency, improper watering, or planting conditions.", "이 장애는 일반적으로 가뭄 스트레스, 영양 결핍, 부적절한 물 공급 또는 재배 조건과 같은 부적절한 환경 조건과 같은 비생물적 요인으로 인해 발생합니다.");
        transMap.put("Water-related abiotic issues refer to abiotic stresses caused by inadequate watering, including water excess, uneven watering, and water deficiency.", "물 관련 비생물학적 문제는 물 과잉, 고르지 않은 물 공급, 물 부족 등 부적절한 물 공급으로 인한 비생물학적 스트레스를 의미합니다.");
        transMap.put("Water excess and uneven watering are abiotic disorders caused by inadequate watering. Water excess may lead to rotting of the roots due to lack of oxygen, and higher susceptibility to infection. Symptoms of over-watering include stunted growth, yellow and brown leaves, wilting and higher susceptibility to leaf burn.", "물 과잉과 고르지 않은 물 공급은 부적절한 물 공급으로 인한 비생물학적 장애입니다. 물이 과도하면 산소 부족으로 인해 뿌리가 썩고 감염에 대한 취약성이 높아질 수 있습니다. 물 과다 공급의 증상으로는 성장 부진, 노란색 및 갈색 잎, 시들음, 잎 화상에 대한 높은 감수성 등이 있습니다.");

        return findInMap(msg, transMap);
    }

    private String healthPreTranslate(String msg){
        // 식물 건강 - 예방
        String res = "";
        Map<String, String> transMap = new HashMap<>();

        // 예방
        transMap.put("Abiotic disorders are caused by non-living factors - usually by unsuitable environmental conditions, such as drought stress, nutrient deficiency, improper watering, or planting conditions.", "이 장애는 일반적으로 가뭄 스트레스, 영양 결핍, 부적절한 물 공급 또는 재배 조건과 같은 부적절한 환경 조건과 같은 비생물적 요인으로 인해 발생합니다.");
        transMap.put("Water-related abiotic issues refer to abiotic stresses caused by inadequate watering, including water excess, uneven watering, and water deficiency.", "물 관련 비생물학적 문제는 물 과잉, 고르지 않은 물 공급, 물 부족 등 부적절한 물 공급으로 인한 비생물학적 스트레스를 의미합니다.");
        transMap.put("Improve soil drainage (e.g. by adding compost to the soil or rocks to the bottom of the pot). Ensure having drainage holes at the bottom of the pot.","토양 배수를 개선하세요(예: 화분 바닥에 퇴비나 돌을 추가하여 토양 배수 개선). 화분 바닥에 배수구가 있는지 확인하세요.");

        return findInMap(msg, transMap);
    }
}
