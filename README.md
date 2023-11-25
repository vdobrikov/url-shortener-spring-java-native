# URL Shortener
## Description
This is a simple URL shortener that I made for fun. It uses a simple algorithm to generate a short URL from a long URL. 
It also has a simple API that can be used to generate a short URL from a long URL and to get the long URL from a short URL.

## How to build

Regular build:
```bash
mvn clean package
```

Build native image (ensure you have GraalVM installed):
```bash
mvn -Pnative native:compile
```

Build native Docker image:
```bash
mvn -Pnative spring-boot:build-image
````

## How to test
Open http://localhost:8080 in your browser and enter a URL to shorten. 
You can also use the API to generate a short URL from a long URL and to get the long URL from a short URL.
```
GET /api/url/short?url=https://example.com
```
