package com.drronidz.training.protobuf.converter;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


/*
PROJECT NAME : protobuf-with-spring-rest-api-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/19/2023 6:03 PM
*/

public class ProtobufUtilUnitTest {

    public static String jsonString = "{\r\n"
            + "  \"boolean\": true,\r\n"
            + "  \"color\": \"gold\",\r\n"
            + "  \"object\": {\r\n"
            + "    \"a\": \"b\",\r\n"
            + "    \"c\": \"d\"\r\n"
            + "  },\r\n"
            + "  \"string\": \"Hello World\"\r\n"
            + "}";

    @Test
    public void given_json_convert_to_protobuf() throws InvalidProtocolBufferException {
        Message protobuf = ProtobufUtil.fromJSON(jsonString);
        Assert.assertTrue(protobuf.toString().contains("key: \"boolean\""));
        Assert.assertTrue(protobuf.toString().contains("string_value: \"Hello World\""));
    }

    @Test
    public void given_protobuf_convert_to_json() throws InvalidProtocolBufferException {
        Message protobuf = ProtobufUtil.fromJSON(jsonString);
        String json = ProtobufUtil.toJSON(protobuf);
        Assert.assertTrue(json.contains("\"boolean\": true"));
        Assert.assertTrue(json.contains("\"string\": \"Hello World\""));
        Assert.assertTrue(json.contains("\"color\": \"gold\""));
    }
}