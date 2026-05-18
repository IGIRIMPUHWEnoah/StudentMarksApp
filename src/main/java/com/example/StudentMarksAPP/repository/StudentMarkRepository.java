package com.example.StudentMarksAPP.repository;

import com.example.StudentMarksAPP.model.StudentMark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, Long> {

    // 1. JPQL Demonstration
    // JPQL uses entity names (StudentMark) and field names (studentName)
    @Query("SELECT s FROM StudentMark s WHERE s.studentName LIKE %:name%")
    Page<StudentMark> findByStudentNameContainingJPQL(@Param("name") String name, Pageable pageable);

    // 2. Native Query Demonstration
    // Native query uses actual database table names (student_marks) and column names (student_name)
    @Query(value = "SELECT * FROM student_marks WHERE student_name LIKE %:name%", 
           countQuery = "SELECT count(*) FROM student_marks WHERE student_name LIKE %:name%",
           nativeQuery = true)
    Page<StudentMark> findByStudentNameContainingNative(@Param("name") String name, Pageable pageable);

    @Query("SELECT s FROM StudentMark s WHERE s.marks>80")
    List<StudentMark> findScore();


}
