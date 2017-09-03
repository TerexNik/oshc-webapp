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
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Long headEmployeeId;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Department parentDepartment;

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

    public Long getHeadEmployeeId() {
        return headEmployeeId;
    }

    public void setHeadEmployeeId(Long headEmployeeId) {
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
