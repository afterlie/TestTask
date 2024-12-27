package com.example.tests.todosTests.put;

import com.example.tests.helper.ApiHelper;
import com.example.tests.helper.DataGenerator;
import com.example.tests.todos.Todo;
import com.example.tests.helper.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.tests.helper.Constants.BASE_URL;
import static com.example.tests.helper.Specifications.*;

public class TodoPutTests {

    ApiHelper apiHelper = new ApiHelper();
    DataGenerator dataGenerator = new DataGenerator();

    @BeforeEach
    void setup() {
        installSpecifications(Specifications.createSpec(BASE_URL));
    }

    @Test
    void putTodos() { //проверка PUT запроса
        Todo todo = new Todo(11, "Buy bread");
        todo.setCompleted(true);
        Response response = apiHelper.putToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpecOK200());
        System.out.println("PUT успешен, тело ответа, как и ожидалось, пустое");
    }

    @Test
    void putTodos2() { //проверка PUT запроса и его перезапись
        Todo todo = new Todo();
        todo.setId(2);
        todo.setText("lyalya");
        todo.setText("rrrrrrr");
        todo.setCompleted(true);
        todo.setCompleted(dataGenerator.getRandomBool());
        Response response = apiHelper.putToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpecOK200());
        System.out.println("PUT перезаписан по последним данным");
    }

    @Test
    void putTodosSeparately(){ //проверка put запроса на отсутствие данных
        Todo todo = new Todo();
        Response response = apiHelper.putToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpec400());
        System.out.println("Обновление недоступно ввиду отсутствия данных");
    }

    @Test
    void putTodosWithoutSomeData(){ //частичное обновление данных
        Todo todo = new Todo();
        todo.setText("Sell cheese");
        Response response = apiHelper.putToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpec400());
        System.out.println("Частичное обновление данных невозможно");
    }

    @Test
    void putTodosWithNoExistID() { //обновление с несуществующим ID
        Todo todo = new Todo(-1, "Sell bread");
        todo.setCompleted(true);
        Response response = apiHelper.putToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpec400());
        System.out.println("Некорректный ID");
    }

    @Test
    void putWithRandomData() { //обновление данных рандомным методом
        Todo todo = new Todo(dataGenerator.getRandomUID(), dataGenerator.getRandomText());
        todo.setCompleted(dataGenerator.getRandomBool());
        Response response = apiHelper.putToDo(todo.getId(), todo.getText(), todo.isCompleted());
        response.then().spec(responseSpec400());
        System.out.println("PUT запрос с рандомными данными, как и ожидалось, невозможен");
    }
}