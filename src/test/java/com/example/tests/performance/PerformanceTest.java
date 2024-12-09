package com.example.tests.performance;

import com.example.tests.helper.DataGenerator;
import constants.Constants;
import constants.RestAssuredSetup;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static constants.Constants.*;
import static io.restassured.RestAssured.given;

public class PerformanceTest {

    @BeforeEach
    void setup() {
        RestAssuredSetup.config();
    }

    @Test
    void testCreateMultipleTodos() throws InterruptedException {
        DataGenerator dataGenerator = new DataGenerator();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            executorService.submit(new Callable<Void>() {
                @Override
                public Void call() {
                    Map<String, Object> requestBody = new HashMap<>();
                    requestBody.put("id", dataGenerator.getRandomUID());
                    requestBody.put("text", dataGenerator.getRandomText());
                    requestBody.put("completed", dataGenerator.getRandomBool());
                    try {
                        long startTime = System.currentTimeMillis(); //
                        Response response =
                                given()
                                        .header("Authorization", Constants.AUTH_TOKEN)
                                        .contentType("application/json")
                                        .body(requestBody)
                                        .when()
                                        .post("/todos");

                        //лог ответа
                        long endTime = System.currentTimeMillis();
                        System.out.println("Request ID: " + dataGenerator.getRandomUID() + " | Status: " + response.statusCode() +
                                " | Time: " + (endTime - startTime) + "ms");

                        //лог ошибки
                        if (response.statusCode() != 201) {
                            System.out.println("Error! Response: " + response.asString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        }

        //ожидаю завершение задачи
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
    }
}