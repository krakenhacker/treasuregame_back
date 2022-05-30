package com.example.treasuregame_back.game;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Game {
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
    private Long id;
    private String name;
    private LocalDateTime timestart;
    private LocalDateTime timeend;
    private double x,y,z,w;

    public Game() {
    }

    public Game(Long id, String name, LocalDateTime timestart, LocalDateTime timeend, double x, double y, double z, double w) {
        this.id = id;
        this.name = name;
        this.timestart = timestart;
        this.timeend = timeend;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Game(String name, LocalDateTime timestart, LocalDateTime timeend, double x, double y, double z, double w) {
        this.name = name;
        this.timestart = timestart;
        this.timeend = timeend;
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

    public LocalDateTime getTimestart() {
        return timestart;
    }

    public void setTimestart(LocalDateTime timestart) {
        this.timestart = timestart;
    }

    public LocalDateTime getTimeend() {
        return timeend;
    }

    public void setTimeend(LocalDateTime timeend) {
        this.timeend = timeend;
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestart=" + timestart +
                ", timeend=" + timeend +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
