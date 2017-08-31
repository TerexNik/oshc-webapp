package ru.OSHC.entity;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
//@SecondaryTable(name = "EMPLOYEE", pkJoinColumns = {
//        @PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "HEAD_EMPLOYEE_ID")
//})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "HEAD_EMPLOYEE_ID", referencedColumnName = "ID")
    private Employee employee;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employee=" + employee +
                '}';
    }
}
