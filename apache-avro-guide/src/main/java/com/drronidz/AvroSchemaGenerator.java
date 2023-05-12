package com.drronidz;

/*
PROJECT NAME : apache-avro-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/12/2023 4:29 PM
*/

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AvroSchemaGenerator {

    public void generateAvroSchema(Class definedClass) throws IOException {
        Schema avroSchema = ReflectData.get().getSchema(definedClass);
        System.out.println(definedClass.getSimpleName());
        File schemaFile = new File("src/main/resources/" + definedClass.getSimpleName()+ "-schema.avsc");
        FileWriter fileWriter = new FileWriter(schemaFile);
        fileWriter.append(avroSchema.toString(true));
        fileWriter.close();
    }
}
