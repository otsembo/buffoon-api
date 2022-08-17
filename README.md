# BUFFOON API
This is a simple api built with KTOR server.

Do not use for production apps.



## ENDPOINTS

- CREATE USER

METHOD = **POST**

ENDPOINT = `/user/create`

REQUEST BODY

    {
        "name":"App Name",
        "age":23,
        "email":"sample.test@mail.com"
    }

RESPONSE

    {
        "status": "success",
        "message": "You have created user: {uid}"
    }

    

- READ LIST OF USERS

METHOD = **GET**

ENDPOINT = `/user/list`

RESPONSE

    [
        {
            "id": 2,
            "name": "App Name",
            "age": 23,
            "email": "sample.test@mail.com"
        },
        {
            "id": 3,
            "name": "John Doe",
            "age": 23,
            "email": "sample.test@mail.com"
        },
        {
            "id": 4,
            "name": "Jane Doe",
            "age": 23,
            "email": "sample.test@mail.com"
        },
        {
            "id": 5,
            "name": "Okwonko",
            "age": 23,
            "email": "okwonko@mail.com"
        },
        {
            "id": 6,
            "name": "Albert Einstein",
            "age": 88,
            "email": "albert@mail.com"
        }
    ]



- UPDATE USER

METHOD = **PUT**

ENDPOINT = `/user/update/{uid}`

REQUEST BODY

    {
        "name":"App Name",
        "age":23,
        "email":"sample.test@mail.com"
    }

RESPONSE

    {
        "status": "success",
        "message": "You have updated user: {uid}"
    }



- DELETE USER

METHOD = **DELETE**

ENDPOINT = `/user/delete/{uid}`

RESPONSE

    {
        "status": "success",
        "message": "You have deleted user: {uid}"
    }

