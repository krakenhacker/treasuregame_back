package com.example.treasuregame_back.GameUsers;

import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.user.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;


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
    private String code;

    public GameUsers() {
    }

    public GameUsers(Game game, User user, String code) {
        this.game = game;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
