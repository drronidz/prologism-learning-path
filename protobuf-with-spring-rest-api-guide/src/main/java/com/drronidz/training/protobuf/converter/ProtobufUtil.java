package com.drronidz.training.protobuf.converter;

/*
PROJECT NAME : protobuf-with-spring-rest-api-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/19/2023 5:44 PM
*/

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;

public class ProtobufUtil {

    public static String toJSON (MessageOrBuilder messageOrBuilder) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(messageOrBuilder);
    }

    public static Message fromJSON (String json) throws InvalidProtocolBufferException {
        Builder structBuilder = Struct.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(json, structBuilder);
        return structBuilder.build();
    }
}
