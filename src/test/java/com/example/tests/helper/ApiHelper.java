package com.example.tests.helper;

import constants.*;
import constants.RestAssuredSetup;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static constants.Constants.TEXT;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    private static RestAssuredSetup restAssuredSetup = new RestAssuredSetup();

    public Response postToDo(int id, String text, boolean bool) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("text", text);
        body.put("completed", bool);
        return given()
                .header("Authorization", Constants.AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post("/todos/")
                .then()
                .log().ifValidationFails()
                .extract().response();
    }

    public Response putToDo(int id, String text, boolean bool) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("text", TEXT);
        body.put("completed", bool);
            return given()
                    .header("Authorization", Constants.AUTH_TOKEN)
                    .contentType(ContentType.JSON)
                    .when()
                    .body(body)
                    .put("/todos/" + 2)
                    .then()
                    .log().all()
                    .extract().response();
    }

    public Response deleteToDo(int id) {
        return given()
                .header("Authorization", Constants.AUTH_TOKEN)
                .when()
                .delete("/todos/" + id)
                .then()
                .log().ifValidationFails()
                .extract().response();
    }

}
