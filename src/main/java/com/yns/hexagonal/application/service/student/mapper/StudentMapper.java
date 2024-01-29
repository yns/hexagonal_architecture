package com.yns.hexagonal.application.service.student.mapper;

import com.yns.hexagonal.adapter.out.jpa.student.entity.StudentEntity;
import com.yns.hexagonal.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper( StudentMapper.class );

    Student toDomain(StudentEntity entity);

    StudentEntity toEntity(Student domainObject);
}
