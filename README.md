# Notification Api

This project aims to develop a rest api about notification system

This project is written in Java 17 under Spring Boot web framework.

In this context You'll see the following in action:
* Layered Architecture
* API rest
* Spring Boot
* Java 17
* Postgres
* Unit tests

## Migration and Seed

You will find that in this project contains schema.sql script to migrate and seed the data. To execute this script you just need to execute `docker-compose up`

### Send notification

**Request**
```
POST /notifications

body:
{
    "category": "Films",
    "message": "New message"
}

```
**Response**
```
200 Ok
{
    "notifications": [
        {
            "id": "6b700816-4b5c-4873-8cfb-b95fa59d4b89",
            "user_id": "f84f42f8-36a5-4abf-a4b6-ce50206dfbca",
            "notified_at": "2023-08-23T01:21:07.873867",
            "category": "Films",
            "channel": "Email",
            "message": "New message"
        },
        {
            "id": "afd81f99-c3f9-4246-a248-493facc137ff",
            "user_id": "32593dd9-92de-4f62-b095-847ed5591709",
            "notified_at": "2023-08-23T01:21:07.882858",
            "category": "Films",
            "channel": "Email",
            "message": "New message"
        },
        {
            "id": "04f95b2e-cf59-4e5f-90dc-0ceb7eb04286",
            "user_id": "32593dd9-92de-4f62-b095-847ed5591709",
            "notified_at": "2023-08-23T01:21:07.888642",
            "category": "Films",
            "channel": "PushNotification",
            "message": "New message"
        },
        {
            "id": "139aa2b3-7c23-4684-97a8-a24acf43cbe2",
            "user_id": "f84f42f8-36a5-4abf-a4b6-ce50206dfbca",
            "notified_at": "2023-08-23T01:21:07.89496",
            "category": "Films",
            "channel": "SMS",
            "message": "New message"
        }
    ]
}
```