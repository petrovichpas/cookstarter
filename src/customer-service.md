### Customer service API

**Проверка токена**

POST /auth
```json5
{
     "token": "test-token" //обязательно
}
```
ответ
```json5
{
    "status": "ACTIVE"
}
```
статусы:
- ACTIVE - действительный токен
- OUT_OF_DATE - просроченный токен
- MISSED - отсутствующий токен