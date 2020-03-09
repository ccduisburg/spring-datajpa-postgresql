package com.concon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student")
public class Student {
    @Id
    @SequenceGenerator(name = "seq_student", allocationSize = 1)//Oracle ve PostresQl database ihtiyac duyuyor. kendisi artiramiyor
    @GeneratedValue(generator = "seq_student", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 100,name="vorname")
    private String vorname;
    @Column(length = 100,name="nachname")
    private String nachname;
    @OneToMany//bir kisinin birden cok adresi olabilir
    @JoinColumn(name="student_adres_id")
    private List<Adress> adresse;

}
