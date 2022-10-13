package com.example.treasuregame_back.GameUsers;

import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.user.User;

import javax.persistence.*;


@Entity
@Table(name = "gameUsers")
public class GameUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "code")
    private int code;

    public GameUsers() {
    }

    public GameUsers(Long id, Game game, User user, int code) {
        this.id = id;
        this.game = game;
        this.user = user;
        this.code = code;
    }

    public GameUsers(User user, int code) {
        this.user = user;
        this.code = code;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game_id) {
        this.game = game_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
