package com.example.tests.helper;

import java.util.Base64;

public class Constants {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String AUTH_TOKEN = "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes());
    public static final int TOTAL_REQUESTS = 50000; //указываю кол-во запросов для нагрузки
    public static final int THREAD_POOL_SIZE = 100; //размер пула параллельных запросов
}
