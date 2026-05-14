package com.example.StudentMarksAPP.repository;

import com.example.StudentMarksAPP.model.StudentMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, Long> {
}
