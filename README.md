JWT Spring Boot example, with Spring Security, JWT token, Role management, Authorization

Example:

To get the token for user or admin. (Admin role covers user role permissions)

curl -X GET http://localhost/userToken

curl -X GET http://localhost/adminToken

To get user / admin resource.

curl -X GET http://localhost/user -H "Authorization: Bearer the-token-from-above"

curl -X GET http://localhost/admin -H "Authorization: Bearer the-token-from-above"

