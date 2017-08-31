package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CERTIFICATE")
//@SecondaryTables({
//        @SecondaryTable(name="SCAN", pkJoinColumns={
//                @PrimaryKeyJoinColumn(name="ID", referencedColumnName="SCAN_ID") })
//})
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    @PrimaryKeyJoinColumn(name = "SCAN_ID", referencedColumnName = "ID")
    private Scan scan;

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

    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", date=" + date +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", scan=" + scan +
                '}';
    }
}
