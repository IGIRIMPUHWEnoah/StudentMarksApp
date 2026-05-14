package com.example.StudentMarksAPP.service;

import com.example.StudentMarksAPP.model.StudentMark;
import com.example.StudentMarksAPP.repository.StudentMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentMarkService {

    private final StudentMarkRepository repository;

    @Autowired
    public StudentMarkService(StudentMarkRepository repository) {
        this.repository = repository;
    }

    public List<StudentMark> getAllMarks() {
        return repository.findAll();
    }

    public Optional<StudentMark> getMarkById(Long id) {
        return repository.findById(id);
    }

    public StudentMark createMark(StudentMark mark) {
        return repository.save(mark);
    }

    public StudentMark updateMark(Long id, StudentMark details) {
        StudentMark mark = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));

        mark.setStudentName(details.getStudentName());
        mark.setSubject(details.getSubject());
        mark.setMarks(details.getMarks());

        return repository.save(mark);
    }

    public void deleteMark(Long id) {
        StudentMark mark = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));
        repository.delete(mark);
    }
}
