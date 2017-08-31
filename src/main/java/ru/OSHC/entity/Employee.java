package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "EMPLOYEE")
//@SecondaryTables({
//        @SecondaryTable(name="POST", pkJoinColumns={
//                @PrimaryKeyJoinColumn(name="ID", referencedColumnName="POST_ID")}),
//        @SecondaryTable(name = "GRADE", pkJoinColumns = {
//                @PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "GRADE_ID")}),
//        @SecondaryTable(name="DEPARTMENT", pkJoinColumns={
//                @PrimaryKeyJoinColumn(name="ID", referencedColumnName="DEPARTMENT_ID")}),
//        @SecondaryTable(name="CERTIFICATE", pkJoinColumns={
//                @PrimaryKeyJoinColumn(name="ID", referencedColumnName="CERTIFICATE_ID")}),
//})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "SALARY")
    private int salary;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "POST_ID", referencedColumnName = "ID")
    private Post post;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "GRADE_ID", referencedColumnName = "ID")
    private Grade grade;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    private Department department ;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "CERTIFICATE_ID", referencedColumnName = "ID")
    private Certificate certificate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", post=" + post +
                ", grade=" + grade +
                ", department=" + department +
                ", certificate=" + certificate +
                '}';
    }
}