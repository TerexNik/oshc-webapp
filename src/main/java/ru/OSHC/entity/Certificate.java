package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "getCertificatesWithNames",
                query = "select c.id, c.certDate, c.company, c.certDate, c.certNumber, s.scan " +
                        "from  Certificate c " +
                        "inner join Scan as s on c.scanId = s.id "
        ),
        @NamedQuery(
                name = "getCertificateById",
                query = "from Certificate c where c.id = :id"
        ),
        @NamedQuery(
                name = "getCertificateList",
                query = "from Certificate"
        )
})

@Entity
@Table
public class Certificate {
    @Id
    private long id;

    @Column
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
