package com.plantplus.plantplus.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilClass {
    public static String getStringDate() {
        // 현재 날짜/시간
        LocalDateTime now = LocalDateTime.now();

        // 현재 날짜/시간 출력
        System.out.println(now);

        // 포맷팅
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        // 포맷팅 현재 날짜/시간 출력
        System.out.println(formatedNow);  // 2021년 06월 17일 06시 43분 21초
        return formatedNow;
    }
}
