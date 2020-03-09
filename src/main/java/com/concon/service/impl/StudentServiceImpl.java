package com.concon.service.impl;


import com.concon.dto.StudentDto;
import com.concon.entity.Adress;
import com.concon.entity.Student;
import com.concon.repository.AdressRepository;
import com.concon.repository.StudentRepository;
import com.concon.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service //dependency implementasyonu icerisinde birtane instance olusturabilmesi icin bu anotasyonu kullaniyoruz
@RequiredArgsConstructor//final kullandigimiz iocin contructer olusturulmak zorunda bu anotasyon consturtor olusturuyor bizim yazmamiza gherek yok
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AdressRepository adressRepository;

    @Override
    @Transactional//Eger kayitta bir field ta hata olursa hepsini geri alir. Yada hepsini bir seferde databank a kaydeder
    public StudentDto save(StudentDto studentDto) {
       // Assert.isNull(studentDto.getVorname(),"here must be a firstname");

        Student student=new Student();
        student.setVorname(studentDto.getVorname());
        student.setNachname(studentDto.getNachname());
        final Student studentDb=studentRepository.save(student);
        List<Adress> list=new ArrayList<>();
        studentDto.getAdresses().forEach(item->{
                Adress adress= new Adress();
                adress.setAdress(item);
                adress.setAdresType(Adress.AdresType.HOME);
                adress.setAktif(true);
                adress.setStudent(studentDb);
                list.add(adress);

        });
        adressRepository.saveAll(list);
        studentDto.setId(studentDb.getId());
        return studentDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<StudentDto> getAll(){
       List<Student> studenten =studentRepository.findAll();
       List<StudentDto> studentDtos=new ArrayList<>();
       studenten.forEach(it->{
           StudentDto studentDto=new StudentDto();
           studentDto.setId(it.getId());
           studentDto.setVorname(it.getVorname());
           studentDto.setNachname(it.getNachname());
           studentDto.setAdresses(it.getAdresse().stream().map(Adress::getAdress).collect(Collectors.toList()));
           studentDtos.add(studentDto);
       });
        return studentDtos;
    }

    @Override
    public Page<StudentDto> All(Pageable pageable) {
        return null;
    }
}
