# Swagger Codegen for Ready! API TestServer

Generates ReadyAPI TestServer recipe skeletons from a Swagger Definition; each operation will 
result in one recipe file containing one REST TestStep for each consumes value and response code; 
if no consumes are defined it will just contain one teststep for each response.

## Usage

You will need [Swagger Codegen](https://github.com/swagger-api/swagger-codegen) locally - 
then clone this repo and build it with 

```
mvn clean package
```

Then you're all set to use it with 

```
java -cp target/testserver-swagger-codegen-1.0.0.jar:<path to swagger-codegen-cli.jar> 
    io.swagger.codegen.SwaggerCodegen generate -l TestServerCodegen 
    -i <url/path to Swagger definition> -o output
```

## Maven properties

If you plan on running the generated recipes with the 
[testrunner-maven-plugin](https://github.com/olensmar/readyapi-testserver-maven-plugin) you can configure the codegen
to replace the host and path parameters with properties references so you can set those values in your maven configuration.
The following properties are available:

- a mavenPropertyPrefix property which will enable this functionality and use its value as the prefix for generated property names
- a pathParamPrefix property which will add an additional prefix to property names that have been generated for path parameters
- an addOperationId boolean property which will add the operationId to property names that have been generated for path parameters. 
If you have path parameters with the same name across multiple operations and want those to be uniquely assignable then set this to true

The outcome will be:
- the generated recipes will have maven property references for the host and any path parameters
- a recipe.properties file containing all extracted path parameters will be generated into the output folder

Copy these into the recipe-directory used by the maven plugin to use them.
