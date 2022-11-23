package it.fcg.exercise.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class User {
    @CsvBindByName
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String surname;
    @CsvBindByName
    @Column(unique = true, nullable = false)
    private String email;
    @CsvBindByName
    private String city;
    @CsvBindByName
    private String address;
    @CsvBindByName
    private String zipCode;
}
