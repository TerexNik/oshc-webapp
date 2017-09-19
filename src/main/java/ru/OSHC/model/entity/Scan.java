package ru.OSHC.model.entity;

import javax.persistence.*;
import java.util.Arrays;

@NamedQueries({
    @NamedQuery(
            name = "getScans",
            query = "select s.id, s.scan " +
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
@Table
public class Scan {
    @Id
    private long id;

    @Column
    private byte[] scan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getScan() {
        return scan;
    }

    public void setScan(byte[] scan) {
        this.scan = scan;
    }

    @Override
    public String toString() {
        return "Scan{" +
                "id=" + id +
                ", scan=" + Arrays.toString(scan) +
                '}';
    }
}
