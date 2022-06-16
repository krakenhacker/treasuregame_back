package com.example.treasuregame_back.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Entity
@Table
public final class Game {

    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "startdate")
    private LocalDate startdate;
    @Column(name = "starttime")
    private LocalTime starttime;
    @Column(name = "duration")
    private Double duration;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;
    @Column(name = "w")
    private double w;
    @Column(name = "z")
    private double z;

    public Game() {
    }

    public Game(Long id, String name, LocalDate startdate, LocalTime starttime,Double duration, double x, double y, double z, double w) {
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.starttime = starttime;
        this.duration = duration;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Game(String name, LocalDate startdate, LocalTime starttime,Double duration, double x, double y, double z, double w) {
        this.name = name;
        this.startdate = startdate;
        this.starttime = starttime;
        this.duration = duration;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalTime getstarttime() {
        return starttime;
    }

    public void setstarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDouble(Double Double) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startdate=" + startdate +
                ", starttime=" + starttime +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
