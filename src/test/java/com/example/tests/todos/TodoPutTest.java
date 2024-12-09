package com.example.tests.todos;

import com.example.tests.helper.ApiHelper;
import com.example.tests.helper.DataGenerator;
import constants.*;
import constants.RestAssuredSetup;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TodoPutTest {

    ApiHelper apiHelper = new ApiHelper();

    @BeforeEach
    void setup() {
        RestAssuredSetup.config();
    }

    @Test
    void putTodos() { //проверка put запроса
        var put = apiHelper.putToDo(2,"", true);
        assert put.statusCode() == 200;
    }

    @Test
    void putTodosSeparately() {
        var put = apiHelper.putToDo(2,"Sell nothing", false);
        assert put.statusCode() == 200;
                given()
                    .log().all()
                    .when()
                    .get("/todos")
                    .then()
                    .statusCode(200)
                    .extract().response();
    }

    @Test
    void putTodosWithoutData() {
        given() //проверка put запроса на отсутствие данных
                .header("Authorization", Constants.AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .put("/todos/3")
                .then()
                .log().ifValidationFails()
                .statusCode(400);
    }

    @Test
    void putTodosWithoutSomeData() {
        given() //проверка put запроса на частичное обновление данных
                .header("Authorization", Constants.AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body("{ \"text\": \"Sell cheese\" }")
                .when()
                .put("/todos/3")
                .then()
                .log().ifValidationFails()
                .statusCode(400);
    }

    @Test
    void putTodosWithNoExistID() {
        given() //проверка на обновление put запроса с несуществующим ID
                .contentType(ContentType.JSON)
                .body("{ \"id\": 2, \"text\": \"Sell bread\", \"completed\": true }")
                .when()
                .put("/todos/0")
                .then()
                .log().ifValidationFails()
                .statusCode(404);
    }

//    @Test
//    void putTodosWithInvalidData() {
//        given() //проверка на обновление put запроса с неверным форматом JSON данных
//                .header("Authorization", Constants.AUTH_TOKEN)
//                .contentType(ContentType.JSON)
//                .body("{ \"id\": 2, \"text\": Sell bread, \"completed\": true }")
//                .when()
//                .put("/todos/10")
//                .then()
//                .log().ifValidationFails()
//                .statusCode(400);
//    }

//    @Test
//    void putTodosWithTooLongText() {
//        String longText = "a".repeat(1000); // 1001 символ
//        given()
//                .contentType(ContentType.JSON)
//                .body("{ \"id\": 3, \"text\": \"" + longText + "\", \"completed\": true }")
//                .when()
//                .put("/todos/3")
//                .then()
//                .log().ifValidationFails()
//                .statusCode(200);
//    }

//    DataGenerator dataGenerator = new DataGenerator();
//    @Test
//    void putTodos1() { //реализация put запроса случайными данными
//        var put = apiHelper.putToDo(dataGenerator.getRandomUID(), dataGenerator.getRandomText(), dataGenerator.getRandomBool());
//        assert put.statusCode() == 200;
//    }
}
