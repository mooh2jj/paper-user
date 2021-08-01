package com.example.paperuser.user.service;

import com.example.paperuser.user.domain.School;
import com.example.paperuser.user.repository.SchoolRepository;
import com.example.paperuser.user.service.helper.SchoolTestHelper;
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
    private SchoolTestHelper schoolTestHelper;
    School school;

    @BeforeEach
    void before() {
        this.schoolRepository.deleteAll();  // 미리 깨끗이 지우고 시작하는 게 좋음!
        this.schoolService = new SchoolService(schoolRepository);
        this.schoolTestHelper = new SchoolTestHelper(this.schoolService);
        school = this.schoolTestHelper.createSchool("test school", "seoul");
    }

    @DisplayName("1. 학교를 생성한다.")
    @Test
    void test_1() {
//        School school = School.builder()
//                .name("test school")
//                .city("seoul")
//                .build();
//        schoolService.save(school);
//        School school = this.schoolTestHelper.createSchool("test school", "seoul");

        var list = schoolRepository.findAll();
        assertEquals(1, list.size());
//        assertEquals("test school", list.get(0).getName());
//        assertEquals("seoul", list.get(0).getCity());
        SchoolTestHelper.assertSchool(list.get(0), "test school", "seoul");
        System.out.println("save_sccuess!");
    }

    @DisplayName("2. 학교를 수정한다.")
    @Test
    void test_2() {
        schoolService.updateName(school.getSchoolId(), "test2 school");
        var list = schoolRepository.findAll();
        assertEquals("test2 school", schoolRepository.findAll().get(0).getName());
        System.out.println("test2_sccuess!");
    }

    @DisplayName("3. 학교를 목록을 가져온다.")
    @Test
    void test_3() {
        var list = schoolService.cities();
        assertEquals(1, list.size());
        assertEquals("seoul", list.get(0));

        schoolTestHelper.createSchool("busan school", "busan");
        list = schoolService.cities();
        assertEquals(2, list.size());   // busan 추가 -> size :2

        System.out.println("test3_sccuess!");
    }

    @DisplayName("4. 지역으로 학교 목록을 가져온다.")
    @Test
    void test_4() {

        var list = schoolService.findAllByCity("seoul");
        assertEquals(1, list.size());

        schoolTestHelper.createSchool("seoul2 school", "seoul");    // seoul지역에 학교 seoul2 school 추가 -> size:2
        list = schoolService.findAllByCity("seoul");
        assertEquals(2, list.size());


        System.out.println("test4_sccuess!");
    }
}