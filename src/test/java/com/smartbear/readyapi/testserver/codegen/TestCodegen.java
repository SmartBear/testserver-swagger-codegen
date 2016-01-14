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

       System.setProperty( "debugOperations", "");

       SwaggerCodegen.main( new String[]{
               "generate",
               "-l", "TestServerCodeGen",
               "-i", "src/test/resources/swagger.json",
               "-o", target.getPath(),
       });

       // add validation code here
       assertTrue( target.exists());
   }
}
