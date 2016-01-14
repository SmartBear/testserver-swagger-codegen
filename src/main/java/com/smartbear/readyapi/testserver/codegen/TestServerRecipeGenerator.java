package com.smartbear.readyapi.testserver.codegen;

import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenType;
import io.swagger.codegen.DefaultCodegen;
import io.swagger.models.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestServerRecipeGenerator extends DefaultCodegen implements CodegenConfig {

    protected String sourceFolder = "recipes";

    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    public String getName() {
        return "TestServerCodeGen";
    }

    public String getHelp() {
        return "Generates ReadyApi TestServer recipes";
    }

    public TestServerRecipeGenerator() {
        super();

        outputFolder = "generated-code/TestServerCodeGen";
        apiTemplateFiles.put( "recipe.mustache", ".json");
        templateDir = "TestServerCodeGen";
    }

    @Override
    public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co, Map<String, List<CodegenOperation>> operations) {
        List<CodegenOperation> group = operations.get(operation.getOperationId());
        if( group == null ){
            group = new ArrayList<CodegenOperation>();
        }

        group.add( co );
        operations.put( operation.getOperationId(), group );
    }

    @Override
    public String apiFileFolder() {
        return outputFolder + "/" + sourceFolder;
    }
}