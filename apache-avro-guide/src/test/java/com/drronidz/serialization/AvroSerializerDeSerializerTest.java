package com.drronidz.serialization;

import com.drronidz.avro.model.Active;
import com.drronidz.avro.model.AvroHttpRequest;
import com.drronidz.avro.model.ClientIdentifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


/*
PROJECT NAME : apache-avro-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/12/2023 4:55 PM
*/

class AvroSerializerDeSerializerTest {

    AvroSerializer serializer;
    AvroDeSerializer deSerializer;
    AvroHttpRequest request;

    @BeforeEach
    void setUp() {
        serializer = new AvroSerializer();
        deSerializer = new AvroDeSerializer();

        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
                .setHostName("localhost")
                .setIpAddress("255.255.255.0")
                .build();

        List<CharSequence> employees = new ArrayList();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        request = AvroHttpRequest.newBuilder()
                .setRequestTime(01l)
                .setActive(Active.YES).setClientIdentifier(clientIdentifier)
                .setEmployeeNames(employees)
                .build();
    }

    @Test
    void when_serialized_using_JSONEncoder_then_object_gets_serialized() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        assertNotNull(data);
        assertTrue(data.length > 0);
    }

    @Test
    void when_serialized_using_BinaryEncoder_then_object_gets_serialized() {
        byte[] data = serializer.serializeAvroHttpRequestBinary(request);
        assertNotNull(data);
        assertTrue(data.length > 0);
    }

    @Test
    void when_deserialize_using_JSONDecoder_the_actual_and_expected_objects_are_equal() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        AvroHttpRequest actualRequest = deSerializer.deSerializeAvroHttpRequestJSON(data);
        assertEquals(actualRequest, request);
        assertTrue(actualRequest.getRequestTime().equals(request.getRequestTime()));
    }

    @Test
    void when_deserialize_using_BinaryDecoder_the_actual_and_expected_objects_are_equal() {
        byte[] data = serializer.serializeAvroHttpRequestBinary(request);
        AvroHttpRequest actualRequest = deSerializer.deSerializeAvroHttpRequestBinary(data);
        assertEquals(actualRequest, request);
        assertTrue(actualRequest.getRequestTime().equals(request.getRequestTime()));
    }
}