package com.drronidz;

/*
PROJECT NAME : apache-avro-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/12/2023 3:55 PM
*/

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class AvroSchemaBuilder {

    public Schema createAvroHttpRequestSchema() {

        Schema clientIdentifier = SchemaBuilder
                .record("ClientIdentifier")
                .namespace("com.drronidz.avro.model")
                .fields()
                .requiredString("hostName")
                .requiredString("ipAddress")
                .endRecord();

        Schema avroHttpRequest = SchemaBuilder
                .record("AvroHttpRequest")
                .namespace("com.drronidz.avro.model")
                .fields()
                .requiredLong("requestTime")
                .name("clientIdentifier")
                    .type(clientIdentifier)
                    .noDefault()
                .name("employeeNames")
                    .type()
                    .array()
                    .items()
                    .stringType()
                    .arrayDefault(null)
                .name("active")
                    .type()
                    .enumeration("Active")
                    .symbols("YES","NO")
                    .noDefault()
                .endRecord();

        return avroHttpRequest;
    }
}
