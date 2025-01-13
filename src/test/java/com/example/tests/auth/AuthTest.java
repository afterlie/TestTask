package com.example.tests.auth;

import com.example.tests.helper.listener.RetryListener;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.example.tests.helper.Specifications.*;
import static io.restassured.RestAssured.given;

@Tag("auth")
@ExtendWith(RetryListener.class)
public class AuthTest {

    @AfterAll
    public static void saveFailed(){
        RetryListener.saveFailedTests();
    }

    @Test
    public void testWrongLogin() {
        given() // удаление данных с неверным логином
                .auth().preemptive().basic("invalid_login", "admin")
                .when()
                .delete("/todos/1")
                .then()
                .log().all()
                .spec(responseSpec400());
        System.out.println("Неверный логин");
    }

    @Test
    public void testWrongPassword() {
        given() //удаление данных с неверным паролем
                .auth().preemptive().basic("admin", "invalid_password")
                .when()
                .delete("/todos/1")
                .then()
                .log().all()
                .spec(responseSpec400());
        System.out.println("Неверный пароль");
    }
}
