package ru.OSHC.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getDepartmentWithNames",
                query = "select d.name, e.name, e.surname" +
                        " from Department d" +
                        " inner join Employee e on d.headEmployeeId = e.id or d.headEmployeeId is null"
        ),
        @NamedQuery(
                name = "getDepartmentById",
                query = "from Department d where d.id = :id"
        ),
        @NamedQuery(
                name = "getDepartmentList",
                query = "from Department d"
        )
})



@Entity
@Table
public class Department {

    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToOne
    @PrimaryKeyJoinColumn
    private Employee headEmployeeId;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
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

    public Employee getHeadEmployeeId() {
        return headEmployeeId;
    }

    public void setHeadEmployeeId(Employee headEmployeeId) {
        this.headEmployeeId = headEmployeeId;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentDepartment=" + parentDepartment +
                '}';
    }
}
