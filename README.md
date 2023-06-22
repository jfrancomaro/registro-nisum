# README

### Guía

Para poder ejecutar este servicio solo deberá ejecutarlo directamente.
Existen 2 endpoints:

* Login (POST /v1/users/login): Se ingresa el usuario que ya está insertado en el archivo data.sql; Solo colocar email y password.
* Register (POST /v1/users): Se ingresa un body según el reto adjuntado, colocar un header Authorization que sale después de ejecutar el Login.

### Otros

Para poder cambiar el patrón y mensaje de la contraseña se debe configurar en la clase RegisterUserRequest.
En resources está la colección del postman y también el diagrama de solución.