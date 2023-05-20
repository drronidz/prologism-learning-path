package com.drronidz.training.protobuf;

/*
PROJECT NAME : protobuf-with-spring-rest-api-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/19/2023 4:41 PM
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drronidz.training.protobuf.BaeldungTraining.Course;
import com.drronidz.training.protobuf.BaeldungTraining.Student;
import com.drronidz.training.protobuf.BaeldungTraining.Student.PhoneNumber;
import com.drronidz.training.protobuf.BaeldungTraining.Student.PhoneType;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    RestTemplate restTemplate (ProtobufHttpMessageConverter httpMessageConverter) {
        return new RestTemplate(Arrays.asList(httpMessageConverter));
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public CourseRepository createTestCourses() {
        Map<Integer, Course> courses = new HashMap<>();
        Course courseOne = Course.newBuilder().setId(1).setCourseName("REST with Spring").addAllStudent(createTestStudent()).build();
        Course courseTwo = Course.newBuilder().setId(2).setCourseName("Learn Spring Security").addAllStudent(createTestStudent()).build();

        courses.put(courseOne.getId(), courseOne);
        courses.put(courseTwo.getId(), courseTwo);

        return new CourseRepository(courses);
    }

    private List<Student> createTestStudent() {
        PhoneNumber phoneNumberOne = createPhone("01234567890", PhoneType.MOBILE);
        Student studentOne =
                createStudent(1, "John", "Doe", "john.doe@baeldung.com", Arrays.asList(phoneNumberOne));

        PhoneNumber phoneNumberTwo = createPhone("09876543210", PhoneType.LANDLINE);
        Student studentTwo =
                createStudent(2, "Richard", "Doe", "richard.doe@baeldung.com", Arrays.asList(phoneNumberTwo));

        PhoneNumber phoneNumberThreeOne = createPhone("123456789", PhoneType.MOBILE);
        PhoneNumber phoneNumberThreeTwo = createPhone("987654321", PhoneType.LANDLINE);
        Student studentThree =
                createStudent(3, "Jane", "Doe", "jane.doe@baeldung.com", Arrays.asList(phoneNumberThreeOne, phoneNumberThreeTwo));

        return Arrays.asList(studentOne, studentTwo, studentThree);
    }

    private Student createStudent(int id, String firstName, String lastName, String email, List<PhoneNumber> phones) {
        return Student.newBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .addAllPhone(phones)
                .build();

    }

    private PhoneNumber createPhone(String number, PhoneType phoneType) {
        return PhoneNumber.newBuilder()
                .setNumber(number)
                .setType(phoneType)
                .build();
    }
}
