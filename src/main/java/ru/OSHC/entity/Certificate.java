package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CERTIFICATE")
public class Certificate {
    @Id
    private long id;

    @Column(name = "RECEIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CERT_NUMBER")
    private long number;

    @Column(name = "SCAN_ID")
    private Long scanId;

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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", date=" + date +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", scan=" + scanId +
                '}';
    }
}
