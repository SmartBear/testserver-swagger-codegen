package com.smartbear.readyapi.testserver.codegen;

import io.swagger.codegen.Codegen;
import io.swagger.codegen.SwaggerCodegen;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class TestCodegen {
   @Test
   public void testCodegen()
   {
       File target = new File( "target/generated-testclient");

       //System.setProperty( "debugOperations", "");
       System.setProperty( "debugSupportingFiles", "");
       System.setProperty( "apitest.mavenPropertyPrefix", "apitest");

       SwaggerCodegen.main( new String[]{
               "generate",
               "-l", "TestServerCodegen",
               "-i", "https://internal-api.swaggerhub.com/apis/swagger-hub/registry-api/1.0.6",
               "-o", target.getPath(),
       });

       // add validation code here
       assertTrue( target.exists());
   }
}
