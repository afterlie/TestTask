package com.example.tests.auth;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {

    @Test
    void testWrongLogin() {
        given() // удаление данных с неверным логином
                .auth().preemptive().basic("invalid_login", "admin")
                .when()
                .delete("/todos/1")
                .then()
                .log().all()
                .statusCode(401);
        System.out.println("Неверный логин");
    }

    @Test
    void testWrongPassword() {
        given() //удаление данных с неверным паролем
                .auth().preemptive().basic("admin", "invalid_password")
                .when()
                .delete("/todos/1")
                .then()
                .log().all()
                .statusCode(401);
        System.out.println("Неверный пароль");
    }
}
