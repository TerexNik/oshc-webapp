package ru.OSHC.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Arrays;

@NamedQueries({
    @NamedQuery(
            name = "getScans",
            query = "select s.id, s.scanFile " +
                    "from Certificate c " +
                    "inner join Scan as s on s.id = c.scanId"
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
@Table
public class Scan {
    @Id
    private long id;

    @Column
    private long scanFile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScan() {
        return scanFile;
    }

    public void setScan(long scan) {
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
