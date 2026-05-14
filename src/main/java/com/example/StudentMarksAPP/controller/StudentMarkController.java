package com.example.StudentMarksAPP.controller;

import com.example.StudentMarksAPP.model.StudentMark;
import com.example.StudentMarksAPP.service.StudentMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-marks")
public class StudentMarkController {

    private final StudentMarkService service;

    @Autowired
    public StudentMarkController(StudentMarkService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentMark> getAllMarks() {
        return service.getAllMarks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentMark> getMarkById(@PathVariable Long id) {
        return service.getMarkById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentMark createMark(@RequestBody StudentMark mark) {
        return service.createMark(mark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentMark> updateMark(@PathVariable Long id, @RequestBody StudentMark details) {
        try {
            return ResponseEntity.ok(service.updateMark(id, details));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long id) {
        try {
            service.deleteMark(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
