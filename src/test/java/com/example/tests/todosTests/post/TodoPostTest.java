package com.example.tests.todosTests.post;

import com.example.tests.helper.ApiHelper;
import com.example.tests.helper.DataGenerator;
import com.example.tests.todos.Todo;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.tests.helper.Constants.BASE_URL;
import static com.example.tests.helper.Specifications.*;

@Tag("post")
public class TodoPostTest {

    DataGenerator dataGenerator = new DataGenerator();
    ApiHelper apiHelper = new ApiHelper();

    @BeforeEach
    void setup() {
        installSpecifications(createSpec(BASE_URL));
    }

    @Test
    void postTodos() { //вставка валидных параметров
        Todo todo = new Todo(dataGenerator.getRandomUID(), dataGenerator.getRandomText());
        todo.setCompleted(dataGenerator.getRandomBool());
        Response response = apiHelper.postToDo(dataGenerator.getRandomUID(),
                dataGenerator.getRandomText(), dataGenerator.getRandomBool());
        response.then().spec(responseSpecOK200());
    }

    @Test
    void postTodosWithRetryData() { //вставка валидных параметров и повторная вставка тех же
        Todo todo = Todo.builder()
                        .id(12)
                        .text("Sell pie")
                        .completed(dataGenerator.getRandomBool())
                        .build();
        Response response = apiHelper.postToDo(todo.getId(), todo.getText(), todo.isCompleted());
                response.then().spec(responseSpecOK200());
        Response response1 = apiHelper.postToDo(todo.getId(), todo.getText(), todo.isCompleted());
                response1.then().spec(responseSpec400());
    }

    @Test
    void postTodosWithEmptyText() { //умение обрабатывать пустое поле
        Todo todo = new Todo();
        todo.setId(2);
        todo.setText("");
        todo.setCompleted(dataGenerator.getRandomBool());
        Response response = apiHelper.postToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpecOK200());
    }

    @Test
    void postTodoWithEmptyBody() { //проверка на создание пустого тела
        Todo todo = new Todo();
        Response response = apiHelper.postToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpec400());
    }

    @Test
    void postTodosWithInvalidData() { //проверка невалидных данных
        Todo todo = new Todo(-1, "it won't work");
        todo.setCompleted(dataGenerator.getRandomBool());
        Response response = apiHelper.postToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpec400());
    }
}
