package ru.OSHC.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getDepartmentWithNames",
                query = "select d.id, d.name, e.name, e.surname" +
                        " from Department d" +
                        " inner join Employee e on d.headEmployeeId = e.id or d.headEmployeeId is null"
        ),
        @NamedQuery(
                name = "getDepartmentById",
                query = "from Department d where d.id = :id"
        )
})

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
