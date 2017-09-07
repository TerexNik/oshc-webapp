package ru.OSHC.entity;


import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "getHistoryList",
                query = "from History g"
        ),
        @NamedQuery(
                name = "getHistoryById",
                query = "from History h where h.id = :id"
        )
})

@Entity
@Table
public class History {

    @Id
    private long id;

    @Column
    private boolean isActive;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Employee employees;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", timestamp=" + timestamp +
                ", employees=" + employees +
                '}';
    }
}
