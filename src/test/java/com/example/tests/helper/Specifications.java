package com.example.tests.helper;

import io.restassured.RestAssured;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

import static com.example.tests.helper.Constants.AUTH_TOKEN;
import static org.hamcrest.Matchers.*;


public class Specifications {
    public static RequestSpecification createSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", AUTH_TOKEN)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(equalTo(200), equalTo(201), equalTo(204)))
                .build();
    }

    public static ResponseSpecification responseSpec400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(equalTo(400), equalTo(401), equalTo(404), equalTo(405)))
                .build();
    }

    public static void installSpecifications(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static void installSpecifications(RequestSpecification request){
        RestAssured.requestSpecification = request;
    }
}