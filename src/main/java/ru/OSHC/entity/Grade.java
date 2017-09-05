package ru.OSHC.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getGradesWithNames",
                query = "select g.name, e.name, e.surname" +
                        " from Employee e" +
                        " left join Grade g on g.id = e.grade"
        ),
        @NamedQuery(
                name = "getGradesList",
                query = "from Grade g"
        ),
        @NamedQuery(
                name = "getGradeById",
                query = "from Grade g where g.id = :id"
        ),
})


@Entity
@Table
public class Grade {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

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

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

