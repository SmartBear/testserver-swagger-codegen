# Swagger Codegen for Ready! API TestServer

Generates ReadyAPI TestServer recipe skeletons from a Swagger Definition; each operation will 
result in one recipe file containing one REST TestStep for each consumes value and response code; 
if no consumes are defined it will just contain one teststep for each response.

After building with mvn:package - use with 

```
java -cp target/testserver-swagger-codegen-1.0.0.jar:<path to swagger-codegen-cli.jar> 
    io.swagger.codegen.SwaggerCodegen generate -l TestServerCodegen 
    -i http://petstore.swagger.io/v2/swagger.json -o output
```


## Maven properties

If you plan on running the generated recipes with the 
(testrunner-maven-plugin)[https://github.com/olensmar/readyapi-testserver-maven-plugin] you can add
 a mavenPropertyPrefix property to the command-line as a system property, which will result in the following:

- the generated recipes will have maven property references for the host and any path parameters
- a recipe.properties file containing all extracted path parameters

Copy these into the recipe-directory used by the maven plugin to use them.
