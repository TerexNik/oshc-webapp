package ru.OSHC.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getDepartmentWithNames",
                query = "select d.id, d.name, d.parentDepartment.name, d.headEmployee, e.name, e.surname" +
                        " from Department d" +
                        " inner join Employee e on d.headEmployee = e.id or d.headEmployee is null"
        ),
        @NamedQuery(
                name = "getDepartmentById",
                query = "from Department d where d.id = :id"
        ),
        @NamedQuery(
                name = "getSubDepartmentsList",
                query = "select d.id, d.name from Department d where d.parentDepartment.id = :id"
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

    @Column(name = "HEADEMPLOYEE_ID")
    private Long headEmployee;

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

    public Long getHeadEmployee() {
        return headEmployee;
    }

    public void setHeadEmployee(Long headEmployeeId) {
        this.headEmployee = headEmployeeId;
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
                ", headEmployee=" + headEmployee +
                ", parentDepartment=" + parentDepartment +
                '}';
    }
}
