package com.example.tests.todosTests.get;

import com.example.tests.helper.ApiHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.tests.helper.Constants.BASE_URL;
import static com.example.tests.helper.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TodoGetTest {

    ApiHelper apiHelper = new ApiHelper();

    @BeforeEach
    void setup() {
        installSpecifications(createSpec(BASE_URL));
    }

    @Test void testTest(){ //получаем данные и убеждаемся, что список не пуст
        Response response = apiHelper.getToDo();
        response.then().spec(responseSpecOK200());
    }

    @Test
    void testGetTodosWithPagination() { //выдача ограниченного кол-ва данных
        Response response = given()
                .queryParam("offset", 0)
                .queryParam("limit", 5)
                .when()
                .get("/todos");
        response
                .then()
                .log().all()
                .spec(responseSpecOK200())
                .body("size()", lessThanOrEqualTo(5));
    }

    @Test
    void testGetTodosWithPagination4() {
        Response response = given() //проверка на невалидный параметр лимита
                .queryParam("offset", 0)
                .queryParam("limit", "hi")
                .when()
                .get("/todos");
        response
                .then()
                .log().all()
                .spec(responseSpec400());
    }

    @Test
    void testGetTodoThatDoesNotExist() {
        Response response = given() //проверка на несуществующий ID
                .when()
                .get("/todos/-5");
        response
                .then()
                .log().all()
                .spec(responseSpec400());
    }
}
