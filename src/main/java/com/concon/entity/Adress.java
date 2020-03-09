package com.concon.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "student_adres")
@ToString
@EqualsAndHashCode(of={"id"})//id ler birbirine esit olan iki obje virvirine esittir
public class Adress implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_student_adres", allocationSize = 1)//Oracle ve PostresQl database ihtiyac duyuyor. kendisi artiramiyor
    @GeneratedValue(generator = "seq_student_adres", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String adress;
    @Enumerated
    private AdresType adresType;
    private Boolean aktif;
    @ManyToOne
    @JoinColumn(name="student_adres_id")
    private Student student;
   public enum AdresType {
        HOME,
        ARBEIT
    }
}
