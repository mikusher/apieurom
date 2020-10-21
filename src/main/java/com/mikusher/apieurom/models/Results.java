package com.mikusher.apieurom.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_RESULTS")
public class Results implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer nOne;
    private Integer nTwo;
    private Integer nThree;
    private Integer nFour;
    private Integer nFive;

    private Integer sOne;
    private Integer sTwo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getnOne() {
        return nOne;
    }

    public void setnOne(Integer nOne) {
        this.nOne = nOne;
    }

    public Integer getnTwo() {
        return nTwo;
    }

    public void setnTwo(Integer nTwo) {
        this.nTwo = nTwo;
    }

    public Integer getnThree() {
        return nThree;
    }

    public void setnThree(Integer nThree) {
        this.nThree = nThree;
    }

    public Integer getnFour() {
        return nFour;
    }

    public void setnFour(Integer nFour) {
        this.nFour = nFour;
    }

    public Integer getnFive() {
        return nFive;
    }

    public void setnFive(Integer nFive) {
        this.nFive = nFive;
    }

    public Integer getsOne() {
        return sOne;
    }

    public void setsOne(Integer sOne) {
        this.sOne = sOne;
    }

    public Integer getsTwo() {
        return sTwo;
    }

    public void setsTwo(Integer sTwo) {
        this.sTwo = sTwo;
    }

}
