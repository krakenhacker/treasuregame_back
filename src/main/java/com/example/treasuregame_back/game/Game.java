package com.example.treasuregame_back.game;

import com.example.treasuregame_back.GameUsers.GameUsers;
import com.example.treasuregame_back.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public final class Game {

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameUsers> gameUsers = new HashSet<>();

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
    @Column(name = "game_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "duration")
    private double duration;

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

    public Game(Long id, String name, LocalDateTime start,double duration, double x, double y, double z, double w) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.duration = duration;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Game(String name, LocalDateTime start,double duration, double x, double y, double z, double w) {
        this.name = name;
        this.start = start;
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

//    public LocalDate getStartdate() {
//        return startdate;
//    }
//
//    public void setStartdate(LocalDate startdate) {
//        this.startdate = startdate;
//    }
//
//    public LocalTime getstarttime() {
//        return starttime;
//    }
//
//    public void setstarttime(LocalTime starttime) {
//        this.starttime = starttime;
//    }

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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", duration=" + duration +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
