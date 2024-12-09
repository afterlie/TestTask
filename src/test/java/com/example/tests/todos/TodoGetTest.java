package com.example.tests.todos;

import constants.Constants;
import constants.RestAssuredSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class TodoGetTest {

    @BeforeEach
    void setup() {
        RestAssuredSetup.config();
    }

    @Test
    void getTodos(){
        given()
                .when()
                .get("/todos")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("size()", greaterThan(0)); //проверяем, что список не пуст
    }

    @Test
    void testGetTodosWithPagination() {
        given()
                .queryParam("offset", 0)
                .queryParam("limit", 5)
                .when()
                .get("/todos")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("size()", lessThanOrEqualTo(5)); //проверяем лимит на выдачу
    }

    @Test
    void testGetTodosWithPagination2() {
        given() //проверка на отсутствие оффсета
                .queryParam("limit", 5)
                .when()
                .get("/todos")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("size()", lessThanOrEqualTo(5));
    }

    @Test
    void testGetTodosWithPagination3() {
        given() //проверка на отсутствие лимита
                .queryParam("offset", 0)
                .when()
                .get("/todos")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test
    void testGetTodosWithPagination4() {
        given() //проверка на неккоретный параметр лимита
                .queryParam("offset", 0)
                .queryParam("limit", "hi")
                .when()
                .get("/todos")
                .then()
                .log().ifValidationFails()
                .statusCode(400);
    }

    @Test
    void testGetTodoThatDoesNotExist() {
        given() //проверка на несуществующий ID
                .header("Authorization", Constants.AUTH_TOKEN)
                .when()
                .get("/todos/99")
                .then()
                .log().ifValidationFails()
                .statusCode(405);
    }
}
