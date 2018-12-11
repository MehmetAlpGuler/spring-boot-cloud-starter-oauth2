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

