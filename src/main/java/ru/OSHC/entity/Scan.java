package ru.OSHC.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Arrays;

@NamedQueries({
    @NamedQuery(
            name = "getScans",
            query = "select s.id, s.scanFile " +
                    "from Scan  s " +
                    "inner join Certificate as c on s.id = c.scan "
    ),
        @NamedQuery(
                name = "getScanById",
                query = "from Scan s where s.id = :id"
        ),
        @NamedQuery(
                name = "getScansList",
                query = "from Scan"
        )
})

@Entity
@Table(name = "SCANS")
public class Scan {
    @Id
    private long id;

    @Column(name = "SCAN")
    private Long scanFile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getScan() {
        return scanFile;
    }

    public void setScan(Long scan) {
        this.scanFile = scan;
    }

    @Override
    public String toString() {
        return "Scan{" +
                "id=" + id +
                ", scan=" + scanFile +
                '}';
    }
}
