package ru.OSHC.entity;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    private long id;

    @Column
    private String name;

    @Column(name = "HEAD_EMPLOYEE_ID")
    private Long headEmployeeId;

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

    public long getHeadEmployeeId() {
        return headEmployeeId;
    }

    public void setHeadEmployeeId(long headEmployeeId) {
        this.headEmployeeId = headEmployeeId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeId=" + headEmployeeId +
                '}';
    }
}
