package ru.OSHC.entity;

import javax.persistence.*;
import java.util.Arrays;

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
