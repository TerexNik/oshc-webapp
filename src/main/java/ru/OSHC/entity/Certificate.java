package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Certificate {
    @Id
    private long id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date certDate;

    @Column
    private String company;

    @Column
    private String certName;

    @Column
    private long certNumber;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Scan scanId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCertDate() {
        return certDate;
    }

    public void setCertDate(Date date) {
        this.certDate = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String name) {
        this.certName = name;
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
                ", date=" + certDate +
                ", company='" + company + '\'' +
                ", certName='" + certName + '\'' +
                ", certNumber=" + certNumber +
                ", scanId=" + scanId +
                '}';
    }
}
