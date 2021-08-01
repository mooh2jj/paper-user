package com.example.paperuser.user.service;

import com.example.paperuser.user.domain.School;
import com.example.paperuser.user.repository.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SchoolServiceTest {

    @Autowired
    private SchoolRepository schoolRepository;

    private SchoolService schoolService;

    @BeforeEach
    void before() {
        this.schoolService = new SchoolService(schoolRepository);
    }

    @DisplayName("1. 학교를 생성한다.")
    @Test
    void save() {
        School school = School.builder()
                .name("test school")
                .city("seoul")
                .build();
        schoolService.save(school);

        var list = schoolRepository.findAll();
        assertEquals(1, list.size());
        assertEquals("test school", list.get(0).getName());
        assertEquals("seoul", list.get(0).getCity());
        System.out.println("save_sccuess!");
    }

    @Test
    void cities() {
    }
}