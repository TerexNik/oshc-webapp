package ru.OSHC.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "getActiveEmployee",
                query = "from Employee e where isActive = true"
        ),
        @NamedQuery(
                name = "getEmployeeById",
                query = "from Employee e where e.historyId = :id and isActive = true"
        ),
        @NamedQuery(
                name = "getEmployeesDepartmentId",
                query = "select e.historyId, e.name, e.surname, e.patronymic, e.salary, e.birthDate" +
                        " from Employee e" +
                        " where e.department.id = :id and isActive = true"
        ),
        @NamedQuery(
                name = "getInactiveEmployee",
                query = "from Employee e where isActive = false"
        ),
        @NamedQuery(
                name = "getEmployeeHistory",
                query = "select e.name, e.surname, e.patronymic, e.salary, e.department.name, e.post.name, e.grade.name," +
                        " e.certificate.certName, e.certificate.company, e.startDate, e.endDate" +
                        " from Employee e where e.historyId = :id"
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
    @GenericGenerator(name = "empl-uuid", strategy = "uuid")
    @GeneratedValue(generator = "empl-uuid")
    private String id;

    @Column(nullable = false)
    private long historyId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column
    private String patronymic;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false)
    private int salary;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column
    private boolean isActive;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
                "id='" + id + '\'' +
                ", historyId=" + historyId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                ", post=" + post +
                ", grade=" + grade +
                ", department=" + department +
                ", certificate=" + certificate +
                '}';
    }
}