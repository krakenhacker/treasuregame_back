package com.example.treasuregame_back.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @NotNull
    private String name;
    @Column(name = "start")
    @NotNull
    private LocalDateTime start;
    @Column(name = "duration")
    @NotNull
    private double duration;

    @Column(name = "x")
    @NotNull
    private double x;
    @Column(name = "y")
    @NotNull
    private double y;
    @Column(name = "w")
    @NotNull
    private double w;
    @Column(name = "z")
    @NotNull
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

    public Game(String name, LocalDateTime start, double duration, double x, double y, double w, double z, GameUsers... gameUsers) {
        this.name = name;
        this.start = start;
        this.duration = duration;
        this.x = x;
        this.y = y;
        this.w = w;
        this.z = z;
        for(GameUsers gameUser : gameUsers) gameUser.setGame(this);
        this.gameUsers = Stream.of(gameUsers).collect(Collectors.toSet());
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
