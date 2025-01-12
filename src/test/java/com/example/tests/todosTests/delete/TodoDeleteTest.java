package com.example.tests.todosTests.delete;

import com.example.tests.helper.Constants;
import com.example.tests.helper.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.example.tests.helper.ApiHelper;

import static com.example.tests.helper.Constants.BASE_URL;
import static com.example.tests.helper.Specifications.*;
import static io.restassured.RestAssured.given;

@Tag("delete")
public class TodoDeleteTest {

    ApiHelper apiHelper = new ApiHelper();
    @BeforeEach
    void setup() {
        installSpecifications(Specifications.createSpec(BASE_URL));
    }

    @Test
    void deleteTodos() { //удаление валидных данных
        Response response = apiHelper.deleteToDo(1);
        response.then().spec(responseSpecOK200());
    }

    @Test
    void deleteTodosTwice() { //удаление данных и попытка удаления уже удаленного
        Response response = apiHelper.deleteToDo(1);
        response.then().spec(responseSpecOK200());
        Response response1 = apiHelper.deleteToDo(1);
        response1.then().spec(responseSpec400());
    }

    @Test
    void deleteTodosNonExistId() { //удаление несуществующего
        Response response = apiHelper.deleteToDo(-2);
        response.then().spec(responseSpec400());
    }
}

//тесты без спецификаций

class TodoDeleteTests {
    @Test
    void deleteTodosWithoutAuth() {
        Response response = given() //удаление без авторизации
                .when()
                .delete("/todos/1");
        response
                .then()
                .log().all()
                .spec(responseSpec400());
        System.out.println("Вы не авторизованы");
    }

        @Test
    void deleteTodosWithIncorrectId() {
        Response response = given() // удаление данных с неверным форматом ID
                .header("Authorization", Constants.AUTH_TOKEN)
                .when()
                .delete("/todos/id");
        response
                .then()
                .log().all()
                .spec(responseSpec400());
    }
}