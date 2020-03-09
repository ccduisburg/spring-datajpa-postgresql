package com.concon.service;

import com.concon.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentDto save(StudentDto studentDto);
    void delete(Long id);
    List<StudentDto> getAll();
    Page<StudentDto> All(Pageable pageable);
}
