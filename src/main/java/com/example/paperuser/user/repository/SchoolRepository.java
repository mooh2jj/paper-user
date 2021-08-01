package com.example.paperuser.user.repository;

import com.example.paperuser.user.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("select distinct(city) from School")
    List<String> getCities();

    List<School> findByCity(String city);
}
