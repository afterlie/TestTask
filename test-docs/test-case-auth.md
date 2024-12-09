# Тест-кейс #1: Удаление данных с неверным логином

**Идентификатор:** TC_AUTH_001  
**Название теста:** Удаление данных с неверным логином  
**Приоритет:** Высокий  
**Тип:** Негативный

## Предусловия:
1. Сервер доступен по базовому URL, настроенному через `RestAssuredSetup.config()`.
2. Ресурс `/todos/1` существует.

## Шаги:
1. Отправить DELETE-запрос на URL `/todos/1` с авторизацией:
    - Логин: `wrong_login`
    - Пароль: `admin`.

## Ожидаемый результат:
1. Сервер возвращает статус-код `401`.
2. Тело ответа (при наличии) сообщает о неверной авторизации.

---

# Тест-кейс 2: Удаление данных с неверным паролем

**Идентификатор:** TC_AUTH_002  
**Название теста:** Удаление данных с неверным паролем  
**Приоритет:** Высокий  
**Тип:** Негативный  

## Предусловия:
1. Сервер доступен по базовому URL, настроенному через `RestAssuredSetup.config()`.
2. Ресурс `/todos/1` существует.

## Шаги:
1. Отправить DELETE-запрос на URL `/todos/1` с авторизацией:  
   - Логин: `admin`  
   - Пароль: `wrong_password`.

## Ожидаемый результат:
1. Сервер возвращает статус-код `401`.
2. Тело ответа (при наличии) сообщает о неверной авторизации.