package com.drronidz;

/*
PROJECT NAME : apache-avro-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/12/2023 4:04 PM
*/

import org.apache.avro.Schema;
import org.apache.avro.compiler.specific.SpecificCompiler;

import java.io.File;
import java.io.IOException;

public class AvroClassGenerator {
    public void generateAvroClasses(Class definedClass) throws IOException {
        SpecificCompiler compiler = new SpecificCompiler(new Schema.Parser().parse(new File("src/main/resources/"+ definedClass.getSimpleName() + "-schema.avsc")));
        compiler.compileToDestination(new File("src/main/resources"), new File("src/main/java"));
    }
}
