package com.example.treasuregame_back.gamePuzzles;

import com.example.treasuregame_back.game.Game;

import javax.persistence.*;

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
    private int puzzle;

    @Column(name = "answer")
    private int answer;

    public gamePuzzles(Game game, int puzzle, int answer) {
        this.game = game;
        this.puzzle = puzzle;
        this.answer = answer;
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

    public int getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int puzzle) {
        this.puzzle = puzzle;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
