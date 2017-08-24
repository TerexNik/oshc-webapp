package ru.OSHC.entity;

import javax.persistence.*
import java.util.Arrays;

@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private long number;

    @Column(name = "scan")
    private byte[] scan;

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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public byte[] getScan() {
        return scan;
    }

    public void setScan(byte[] scan) {
        this.scan = scan;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", scan=" + Arrays.toString(scan) +
                '}';
    }
}
