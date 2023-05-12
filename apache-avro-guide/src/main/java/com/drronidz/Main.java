package com.drronidz;

/*
PROJECT NAME : apache-avro-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/12/2023 4:23 PM
*/

import com.drronidz.avro.model.AvroHttpRequest;
import com.drronidz.avro.model.Employee;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        AvroSchemaBuilder avroSchemaBuilder = new AvroSchemaBuilder();
        AvroSchemaGenerator avroSchemaGenerator = new AvroSchemaGenerator();
        AvroClassGenerator avroClassGenerator = new AvroClassGenerator();
        avroClassGenerator.generateAvroClasses(Employee.class);
        avroSchemaGenerator.generateAvroSchema(AvroHttpRequest.class);
        avroSchemaGenerator.generateAvroSchema(Employee.class);
    }
}
