# Swagger Codegen for Ready! API TestServer

Generates ReadyAPI TestServer recipe skeletons from a Swagger Definition; each operation will 
result in one recipe file containing one REST TestStep for each consumes value; if no consumes
are defined it will just contain one teststep.

After building with mvn:package - use with 

```
java -cp target/testserver-swagger-codegen-1.0.0.jar:<path to swagger-codegen-cli.jar> 
    io.swagger.codegen.Codegen generate -l TestServerCodegen 
    -i http://petstore.swagger.io/v2/swagger.json -o output
```
