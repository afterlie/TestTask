# Todo API Tests

## Описание
Тесты для проверки CRUD операций и авторизации.

## Запуск тестов
0. Подключитесь к docker run-p 8080:4242 todo-app:latest
1. Убедитесь, что API запущен на `localhost:8080`
2. Пройдите по пути src/test/java/com/example/tests
3. Запустите GET тесты в /todos
4. Запустите POST тесты в /todos
5. Запустите PUT тесты в /todos
6. Запустите DELETE тесты в /todos
7. Запустите PUT тесты в /todos
8. Запустите AUTH тесты в /todos
9. Запустите Performance тест в /todos
10. Запустите Websocket тест в /todos

## Зависимости
- JUnit 5
- RestAssured
- Websocket/javax