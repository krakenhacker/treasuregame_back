package com.example.treasuregame_back.Models;

import com.example.treasuregame_back.Models.Game;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gamePuzzles")
public class gamePuzzles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "puzzle")
    @NotNull
    private String puzzle;

    @Column(name = "answer")
    @NotNull
    private String answer;

    @Column(name = "x")
    @NotNull
    private double x;
    @Column(name = "y")
    @NotNull
    private double y;

    @Column(name="hint")
    @NotNull
    private String hint;

    public gamePuzzles(Long id, Game game, String puzzle, String answer, double x, double y, String hint) {
        this.id = id;
        this.game = game;
        this.puzzle = puzzle;
        this.answer = answer;
        this.x = x;
        this.y = y;
        this.hint = hint;
    }

    public gamePuzzles(Game game, String puzzle, String answer, double x, double y, String hint) {
        this.game = game;
        this.puzzle = puzzle;
        this.answer = answer;
        this.x = x;
        this.y = y;
        this.hint = hint;
    }

    public gamePuzzles() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}