package com.yns.hexagonal.application.port.in;

import com.yns.hexagonal.domain.Student;

import java.util.List;

public interface StudentServicePort {

    List<Student> getStudents();

    Student getStudentById(Long id);

    Student createStudent(Student student);

    void removeStudent(Long id);
}
