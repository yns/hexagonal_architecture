package com.yns.hexagonal.application.service.student;

import com.yns.hexagonal.adapter.out.jpa.student.entity.StudentEntity;
import com.yns.hexagonal.application.port.in.StudentServicePort;
import com.yns.hexagonal.application.port.out.StudentJpaPort;
import com.yns.hexagonal.application.service.student.mapper.StudentMapper;
import com.yns.hexagonal.domain.Student;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServicePort {

    private final StudentJpaPort repository;

    private final StudentMapper mapper = StudentMapper.INSTANCE;

    public StudentService(StudentJpaPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getStudents() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<StudentEntity> optionalStudent = repository.findById(id);
        return optionalStudent.map(mapper::toDomain).orElse(null);
    }

    @Override
    public Student createStudent(Student student) {
        StudentEntity entity = mapper.toEntity(student);

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void removeStudent(Long id) {
        Optional<StudentEntity> optionalStudent = repository.findById(id);

        if (optionalStudent.isEmpty()) {
            throw new ResourceNotFoundException("İlgili öğrenci bulunamadı");
        }

        repository.delete(optionalStudent.get());
    }
}
