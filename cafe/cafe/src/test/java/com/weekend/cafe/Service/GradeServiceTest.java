package com.weekend.cafe.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeServiceTest {

    @Autowired
    GradeService gradeService;

    @Test
    void getAllGrade() {
        System.out.println(gradeService.getAllGrade());
    }
}