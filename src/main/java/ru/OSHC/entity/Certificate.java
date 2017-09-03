package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    @Column
    private Date date;

    @Column
    private String company;

    @Column
    private String name;

    @Column
    private long certNumber;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Scan scanId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(long certNumber) {
        this.certNumber = certNumber;
    }

    public Scan getScanId() {
        return scanId;
    }

    public void setScanId(Scan scanId) {
        this.scanId = scanId;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", date=" + date +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", number=" + certNumber +
                ", scan=" + scanId +
                '}';
    }
}
