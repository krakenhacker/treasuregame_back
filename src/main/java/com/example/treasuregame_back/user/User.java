package com.example.treasuregame_back.user;

import com.example.treasuregame_back.game.Game;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "User_Game",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "game_id") }
    )
    Set<Game> games = new HashSet<>();

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "code")
    private String code;

    public User() {
    }

    public User(String nickname, String email, String code) {
        this.nickname = nickname;
        this.email = email;
        this.code = code;
    }

    public User(Set<Game> games, Long id, String nickname, String email, String code) {
        this.games = games;
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.code = code;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
