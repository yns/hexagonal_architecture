package com.yns.hexagonal.application.port.out;

import com.yns.hexagonal.adapter.out.jpa.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaPort extends JpaRepository<StudentEntity, Long> {

}
