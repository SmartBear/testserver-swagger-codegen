package com.smartbear.readyapi.testserver.codegen;

import com.google.common.collect.Lists;
import io.swagger.codegen.CliOption;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenType;
import io.swagger.codegen.DefaultCodegen;
import io.swagger.codegen.SupportingFile;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestServerRecipeGenerator extends DefaultCodegen implements CodegenConfig {

    protected String sourceFolder = "recipes";
    private String propertyPrefix;

    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    public String getName() {
        return "TestServerCodegen";
    }

    public String getHelp() {
        return "Generates ReadyApi TestServer recipes";
    }

    public TestServerRecipeGenerator() {
        super();

        cliOptions.add( new CliOption( "mavenPropertyPrefix", "replaces path parameters and host with property references"));

        outputFolder = "generated-code/TestServerCodeGen";
        apiTemplateFiles.put( "recipe.mustache", ".json");
        templateDir = "TestServerCodeGen";

        supportingFiles.add(new SupportingFile("props.mustache", "", "props.txt"));
    }

    @Override
    public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co, Map<String, List<CodegenOperation>> operations) {
        List<CodegenOperation> group = operations.get(operation.getOperationId());
        if( group == null ){
            group = new ArrayList<CodegenOperation>();
        }

        if( propertyPrefix != null ){
            co.path = co.path.replace("{", "${" + propertyPrefix + "." + co.operationId + "." );
        }

        group.add( co );
        operations.put( operation.getOperationId(), group );
    }

    @Override
    public void preprocessSwagger(Swagger swagger) {
        propertyPrefix = (String)additionalProperties().get("mavenPropertyPrefix");

        if( propertyPrefix != null ){
            swagger.setHost( "${" + propertyPrefix + ".host}" );

            List<PathParameterProperty> pathParamProperties = new ArrayList<>();
            Set<String> names = new HashSet<>();

            for( Path path : swagger.getPaths().values()){
                for( Operation operation : path.getOperations())
                {
                    for( Parameter parameter : operation.getParameters()){
                        if( parameter instanceof PathParameter ){
                            String property = propertyPrefix + "." + operation.getOperationId() + "." + parameter.getName();
                            if( !names.contains(property)) {
                                pathParamProperties.add(new PathParameterProperty(property, ((PathParameter) parameter).getDefaultValue()));
                                names.add( property );
                            }
                        }
                    }
                }
            }

            additionalProperties.put( "mavenProperties", pathParamProperties );
        }
    }

    static class PathParameterProperty {
        public final String name;
        public final String defaultValue;

        public PathParameterProperty(String name, String defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }
    }

    @Override
    public String apiFileFolder() {
        return outputFolder + "/" + sourceFolder;
    }
}