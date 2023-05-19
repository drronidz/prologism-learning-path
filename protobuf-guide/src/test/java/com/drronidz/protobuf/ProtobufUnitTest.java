package com.drronidz.protobuf;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


/*
PROJECT NAME : protobuf-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/19/2023 3:19 PM
*/

class ProtobufUnitTest {

    private final String filePath  = "address_book";


    @AfterEach
    void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }

    @Test
    void givenGeneratedProtobufClass_whenCreateClass_thenShouldCreateJavaInstance() {
        // When
        String email = "john.doe@baeldung.com";
        int id = new Random().nextInt();
        String name = "John Program";
        String number = "01234567890";
        AddressBookProtos.Person person =
                AddressBookProtos.Person.newBuilder()
                        .setId(id)
                        .setName(name)
                        .setEmail(email)
                        .addNumbers(number)
                        .build();
        // Then
        assertEquals(person.getEmail(),email);
        assertEquals(person.getId(),id);
        assertEquals(person.getName(),name);
        assertEquals(person.getNumbers(0),number);
    }

    @Test
    void givenAddressBookWithOnePerson_whenSaveAsFile_shouldLoadFromFileToJavaClass() throws IOException {

        // Given
        String email = "john.doe@baeldung.com";
        int id = new Random().nextInt();
        String name = "John Program";
        String number = "01234567890";
        AddressBookProtos.Person person =
                AddressBookProtos.Person.newBuilder()
                        .setId(id)
                        .setName(name)
                        .setEmail(email)
                        .addNumbers(number)
                        .build();

        // When
        AddressBookProtos.AddressBook addressBook =
                AddressBookProtos.AddressBook
                        .newBuilder()
                        .addPeople(person)
                        .build();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        addressBook.writeTo(fileOutputStream);
        fileOutputStream.close();

        // Then
        FileInputStream fileInputStream = new FileInputStream(filePath);
        AddressBookProtos.AddressBook deserialized =
                AddressBookProtos.AddressBook.newBuilder().mergeFrom(fileInputStream).build();
        fileInputStream.close();
        assertEquals(deserialized.getPeople(0).getEmail(), email);
        assertEquals(deserialized.getPeople(0).getId(), id);
        assertEquals(deserialized.getPeople(0).getName(), name);
        assertEquals(deserialized.getPeople(0).getNumbers(0), number);
    }
}