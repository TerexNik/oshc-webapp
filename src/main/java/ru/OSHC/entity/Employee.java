package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "getEmployeeWithNames",
                query = "select e.id, e.name, e.surname, e.birthDate, e.salary, d.name, p.name, g.name, c.name " +
                        "from  Employee as e " +
                        "inner join Department as d on e.departmentId = d.id " +
                        "inner join Post as p on e.postId = p.id " +
                        "inner join Grade as g on e.gradeId = g.id " +
                        "inner join Certificate as c on e.certificateId = c.id or e.certificateId is null"
        ),
        @NamedQuery(
                name = "getEmployeeById",
                query = "from Employee e where e.id = :id"
        )
})

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @PrimaryKeyJoinColumn
    private Post postId;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Grade gradeId;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Department departmentId ;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Certificate certificateId;

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

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public Grade getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grade gradeId) {
        this.gradeId = gradeId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Certificate getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Certificate certificateId) {
        this.certificateId = certificateId;
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
                ", postId=" + postId +
                ", gradeId=" + gradeId +
                ", departmentId=" + departmentId +
                ", certificateId=" + certificateId +
                '}';
    }
}