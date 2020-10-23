package com.mikusher.apieurom.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "TB_RESULTS")
public class Results implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String date;

    @ElementCollection
    private List<String> balls_drawn = new ArrayList<>();

    @ElementCollection
    private List<String> lucky_stars = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getBallsDrawn() {
        return Collections.unmodifiableList(balls_drawn);
    }

    public void setBallsDrawn(Collection<String> balls_drawn) {
        this.balls_drawn = new ArrayList<>(balls_drawn)  ;
    }

    public List<String> getLuckyStars() {
        return Collections.unmodifiableList(lucky_stars);
    }

    public void setLuckyStars(Collection<String> lucky_stars) {
        this.lucky_stars = new ArrayList<>(lucky_stars);
    }

}
