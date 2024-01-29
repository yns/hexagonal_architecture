package com.yns.hexagonal.adapter.in.rest.student;

import com.yns.hexagonal.domain.Student;
import com.yns.hexagonal.application.port.in.StudentServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    public StudentController(StudentServicePort studentService) {
        this.studentService = studentService;
    }

    private final StudentServicePort studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student s = studentService.getStudentById(id);

        return ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student s = studentService.createStudent(student);

        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.removeStudent(id);

        return ResponseEntity.ok().build();
    }
}
