package com.example.tests.todos;

import com.example.tests.helper.ApiHelper;
import com.example.tests.helper.DataGenerator;
import constants.Constants;
import constants.RestAssuredSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TodoPostTest {

    DataGenerator dataGenerator = new DataGenerator();
    ApiHelper apiHelper = new ApiHelper();

    @BeforeEach
    void setup() {
        RestAssuredSetup.config();
    }

    @Test
    void postTodos() { //вставка валидных параметров
        var post = apiHelper.postToDo(dataGenerator.getRandomUID(),
                dataGenerator.getRandomText(), dataGenerator.getRandomBool());
        assert post.statusCode() == 201;
    }

    @Test
    void postTodosWithRetryData() { //вставка валидных параметров и повторная вставка тех же
        var post2 = apiHelper.postToDo(1, "Buy cheese", true);
        assert post2.statusCode() == 201;
        var post3 = apiHelper.postToDo(1, "Buy cheese", true);
        assert post3.statusCode() == 400;
    }

    @Test
    void postTodosWithEmptyText() { //умение обрабатывать пустое поле
        var empty = apiHelper.postToDo(2, "", true);
        assert empty.statusCode() == 201;
    }

    @Test
    void testCreateTodoWithEmptyBody() {
        given() //проверка на создание пустого тела
                .header("Authorization", Constants.AUTH_TOKEN)
                .contentType("application/json")
                .body("{}")
                .when()
                .post("/todos")
                .then()
                .log().ifValidationFails()
                .statusCode(400);
    }

    @Test
    void postTodosWithNoValidData() {
        given() //проверка невалидных данных
                .contentType("application/json")
                .body("{ \"completed\": false }")
                .when()
                .post("/todos")
                .then()
                .log().ifValidationFails()
                .statusCode(400);
    }

//    @Test
//    void postTodosWithoutData() {
//        given() //проверка на создание пустого объекта
//                .contentType("application/json")
//                .body("{ \"id\": 3 }")
//                .when()
//                .post("/todos")
//                .then()
//                .log().ifValidationFails()
//                .statusCode(400);
//    }
}
