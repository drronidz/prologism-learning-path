package com.drronidz.training.protobuf;

import com.googlecode.protobuf.format.JsonFormat;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.containsString;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.client.RestTemplate;

import com.drronidz.training.protobuf.BaeldungTraining.Course;

import java.io.IOException;
import java.io.InputStream;

/*
PROJECT NAME : protobuf-with-spring-rest-api-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/19/2023 5:26 PM
*/

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    private static final String PORT = "8080";
    private static final String COURSE_ONE_URL = "http://localhost:" + PORT + "/courses/1";

    @Autowired
    private RestTemplate restTemplate;




    @Test
    public void when_using_rest_template_then_succeed() {
        ResponseEntity<Course> course = restTemplate.getForEntity(COURSE_ONE_URL, Course.class);
        assertResponse(course.toString());
    }

    @Test
    public void when_using_http_client_then_succeed() throws IOException {
        InputStream responseStream = executeHttpRequest(COURSE_ONE_URL);
        String jsonOutput = convertProtobufMessagesToJsonString(responseStream);
        assertResponse(jsonOutput);
    }

    private String convertProtobufMessagesToJsonString(InputStream protobufStream) throws IOException {
        JsonFormat jsonFormat = new JsonFormat();
        Course course = Course.parseFrom(protobufStream);
        return jsonFormat.printToString(course);
    }

    private InputStream executeHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(request);
        return httpResponse.getEntity().getContent();
    }

    private void assertResponse(String response) {
        assertThat(response, containsString("id"));
        assertThat(response, containsString("course_name"));
        assertThat(response, containsString("REST with Spring"));
        assertThat(response, containsString("student"));
        assertThat(response, containsString("first_name"));
        assertThat(response, containsString("last_name"));
        assertThat(response, containsString("email"));
        assertThat(response, containsString("john.doe@baeldung.com"));
        assertThat(response, containsString("richard.doe@baeldung.com"));
        assertThat(response, containsString("jane.doe@baeldung.com"));
        assertThat(response, containsString("phone"));
        assertThat(response, containsString("number"));
        assertThat(response, containsString("type"));
    }
}