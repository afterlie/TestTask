package com.example.tests.helper;

import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiHelper {

    public Response getToDo(){
        return given()
                .when()
                .get("/todos")
                .then()
                .log().all()
                .body("size()", greaterThan(0))
                .extract().response();
    }

    public Response postToDo(int id, String text, boolean bool) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("text", text);
        body.put("completed", bool);
        return given()
                .when()
                .body(body)
                .post("/todos/")
                .then()
                .log().all()
                .extract().response();
    }

    public Response putToDo(int id, String text, boolean bool) {

        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("text", text);
        body.put("completed", bool);
            return given()
                    .when()
                    .body(body)
                    .put("/todos/" + id)
                    .then()
                    .log().all()
                    .extract().response();
    }

    public Response deleteToDo(int id) {
        return given()
                .when()
                .delete("/todos/" + id)
                .then()
                .log().all()
                .extract().response();
    }
}
