package ru.OSHC.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "getEmployeeWithNames",
                query = "select e.id, e.name, e.surname, e.birthDate, e.salary, d.name, p.name, g.name, c.certName " +
                        "from  Employee as e " +
                        "inner join Department d on e.department.id = d.id " +
                        "inner join Post as p on e.post.id = p.id " +
                        "inner join Grade as g on e.grade.id = g.id " +
                        "inner join Certificate as c on e.certificate.id = c.id or e.certificate is null"
        ),
        @NamedQuery(
                name = "getEmployeeById",
                query = "from Employee e where e.id = :id"
        ),
        @NamedQuery(
                name = "getEmployeesDepartmentId",
                query = "select e.name, e.surname, e.fatherName, e.salary, e.birthDate, e.post.name, e.grade.name" +
                        " from Employee e" +
                        " where e.department.id = :id"
        ),
        @NamedQuery(
                name = "getEmployeeList",
                query = "from Employee e"
        )
})


@Entity
@Table
public class Employee {

    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column
    private String fatherName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false)
    private int salary;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @PrimaryKeyJoinColumn
    private Post post;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @PrimaryKeyJoinColumn
    private Grade grade;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @PrimaryKeyJoinColumn
    private Department department;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @PrimaryKeyJoinColumn
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

    public void setPost(Post postId) {
        this.post = postId;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade gradeId) {
        this.grade = gradeId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departmentId) {
        this.department = departmentId;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificateId) {
        this.certificate = certificateId;
    }

    public Employee(String name1) {
        this.name = name1;
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