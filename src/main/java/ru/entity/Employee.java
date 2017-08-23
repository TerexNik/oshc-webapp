package ru.entity;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "salary")
    private int salary;

    @Column(name = "grade")
    private String grade;
}
