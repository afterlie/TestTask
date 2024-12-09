package com.example.tests.todos;

import constants.RestAssuredSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.tests.helper.ApiHelper;

import static io.restassured.RestAssured.given;

public class TodoDeleteTest {

    ApiHelper apiHelper = new ApiHelper();
    @BeforeEach
    void setup() {
        RestAssuredSetup.config();
    }

    @Test
    void deleteTodos() {
        var del = apiHelper.deleteToDo(1);
        assert del.statusCode() == 204;
    }

    @Test
    void deleteTodosTwice() {
        var delTwice = apiHelper.deleteToDo(2);
        assert delTwice.statusCode() == 204; // удаление данных и попытка удаления уже удаленного
        var delTwice2 = apiHelper.deleteToDo(2);
        assert delTwice2.statusCode() == 404;
    }

    @Test
    void deleteTodosNonExistId() {
        var nonEx = apiHelper.deleteToDo(111); //удаление несуществующего
        assert nonEx.statusCode() == 404;
    }

    @Test
    void deleteTodosIncorrectToken() {
        given() // удаление с невалидным токеном
                .header("Authorization", "Constants.AUTH_TOKEN")
                .when()
                .delete("/todos/1")
                .then()
                .log().ifValidationFails()
                .statusCode(401);
    }

    @Test
    void deleteTodosWithoutAuth() {
        given() // удаление без авторизации
                .when()
                .delete("/todos/1")
                .then()
                .log().ifValidationFails()
                .statusCode(401);
    }

    //<-предложения на будущее->//

//    @Test
//    void deleteTodosMultiple() { // множественное удаление данных
//        var multi = apiHelper.deleteToDo(3);
//        assert multi.statusCode() == 204;
//        var multi2 = apiHelper.deleteToDo(3);
//        assert multi2.statusCode() == 204;
//    }

//    @Test
//    void deleteAdminId() {
//        given() // удаление id, который принадлежит пользователю админ (кейс на будущее)
//                .header("Authorization", "Constants.AUTH_TOKEN")
//                .when()
//                .delete("/todos/11?user=admin")
//                .then()
//                .statusCode(400);
//    }

//    @Test
//    void deleteTodosWithIncorrectId() {
//        given() // удаление данных с неверным форматом ID
//                .header("Authorization", Constants.AUTH_TOKEN)
//                .when()
//                .delete("/todos/id")
//                .then()
//                .log().ifValidationFails()
//                .statusCode(400);
//    }
}
