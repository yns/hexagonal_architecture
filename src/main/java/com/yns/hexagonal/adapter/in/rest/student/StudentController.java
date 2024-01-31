package com.yns.hexagonal.adapter.in.rest.student;

import com.yns.hexagonal.domain.Student;
import com.yns.hexagonal.application.port.in.StudentServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class.getName());

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    public StudentController(StudentServicePort studentService) {
        this.studentService = studentService;
    }

    private final StudentServicePort studentService;

    @GetMapping
    public List<Student> getStudents() {
        logger.info("Incoming request at {} for request get Student List", applicationName);
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        logger.info("Incoming request at {} for request get one Student", applicationName);
        Student s = studentService.getStudentById(id);

        return ResponseEntity.ok(s);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        logger.info("Incoming request at {} for request create Student", applicationName);
        Student s = studentService.createStudent(student);

        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        logger.info("Incoming request at {} for request delete Student", applicationName);
        studentService.removeStudent(id);

        return ResponseEntity.ok().build();
    }
}
