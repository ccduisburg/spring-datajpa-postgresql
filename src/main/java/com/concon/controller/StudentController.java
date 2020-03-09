package com.concon.controller;

import com.concon.dto.StudentDto;
import com.concon.entity.Student;
import com.concon.service.StudentService;
import com.concon.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping//tek post oldugu source icin adres eklemeye gerek yok
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto student){
        return ResponseEntity.ok(studentService.save(student));
    }
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> studenten(){
        return ResponseEntity.ok(studentService.getAll());
    }
}
