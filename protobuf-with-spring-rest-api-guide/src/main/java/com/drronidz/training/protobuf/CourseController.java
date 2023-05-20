package com.drronidz.training.protobuf;

/*
PROJECT NAME : protobuf-with-spring-rest-api-guide
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 5/19/2023 5:15 PM
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.drronidz.training.protobuf.BaeldungTraining.Course;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/courses/{id}")
    Course customer(@PathVariable Integer id){
        return courseRepository.getCourseById(id);
    }
}
