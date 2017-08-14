Problem description:

Downloading an attachment with mime-type application/zip via swagger-ui gets the file corrupted.

The same file downloaded with application/octet-stream gets downloaded ok

spring-boot-1.5.6.RELEASE
springfox-swagger2, springfox-spring-web and swagger-ui 2.7.0

How to reproduce:

./gradlew bootRun

Open http://http://localhost:8080/swagger-ui.html

Click test-controller -> /test/octet -> Try it out -> Download test.zip -> it's ok (see src/main/resources/test.zip)

Click test-controller -> /test/zip   -> Try it out -> Download test.zip -> it's corrupted (see src/main/resources/testDownload.zip)