package com.drronidz.training.protobuf;

/*
PROJECT NAME : protobuf-with-spring-rest-api-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/19/2023 5:13 PM
*/

import java.util.Map;
import com.drronidz.training.protobuf.BaeldungTraining.Course;

public class CourseRepository {
    private final Map<Integer, Course> courses;

    public CourseRepository(Map<Integer, Course> courses) {
        this.courses = courses;
    }

    public Course getCourseById(int id) {
        return courses.get(id);
    }
}
