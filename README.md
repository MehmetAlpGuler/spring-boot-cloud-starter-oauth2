# spring-boot-cloud-starter-oauth2
AccessTokenStore Redis, Spring boot, postgres


# generate docker image
docker-compose up


# Authorization Server
curl http://localhost:8080/oauth/token -d "grant_type=password&username=user&password=user&client_id=client_id&client_secret=client_secret"

curl http://localhost:8080/oauth/token -d "grant_type=password&username=admin&password=admin&client_id=client_id&client_secret=client_secret"

curl http://localhost:8080/oauth/token -d "grant_type=password&username=other&password=other&client_id=client_id&client_secret=client_secret"

curl http://localhost:8080/oauth/token -d "grant_type=client_credentials&client_id=client_id&client_secret=client_secret"

# list all keys
KEYS *

# Resource Server run and test (redis token store)

curl http://localhost:8080/oauth/token -d "grant_type=password&username=user&password=user&client_id=client_id&client_secret=client_secret"
curl -H "Authorization: Bearer <<access_token>>" http://localhost:8081/api/userMethod

curl http://localhost:8080/oauth/token -d "grant_type=password&username=admin&password=admin&client_id=client_id&client_secret=client_secret"
curl -H "Authorization: Bearer <<access_token>>" http://localhost:8081/api/adminMethod

curl http://localhost:8080/oauth/token -d "grant_type=password&username=other&password=other&client_id=client_id&client_secret=client_secret"
curl -H "Authorization: Bearer <<access_token>>" http://localhost:8081/api/otherMethod

curl http://localhost:8080/oauth/token -d "grant_type=client_credentials&client_id=client_id&client_secret=client_secret"
curl -H "Authorization: Bearer <<access_token>>" http://localhost:8081/api/otherMethod




# Authorization Server
curl http://localhost:8080/oauth/token -d "grant_type=password&username=user&password=user&client_id=client_id&client_secret=client_secret"
curl http://localhost:8080/oauth/token -d "grant_type=password&username=admin&password=admin&client_id=client_id&client_secret=client_secret"
curl http://localhost:8080/oauth/token -d "grant_type=password&username=other&password=other&client_id=client_id&client_secret=client_secret"

# Resource Server
curl -H "Authorization: Bearer b318a127-786a-4cee-88bf-7d777273c1e4" http://localhost:8081/api/userMethod
curl -H "Authorization: Bearer 6f5cc2b9-60aa-42d2-8d44-a4eae33ed84d" http://localhost:8081/api/adminMethod
curl -H "Authorization: Bearer 00c0dcc9-a94a-4324-ad29-6790d4af5eeb" http://localhost:8081/api/otherMethod