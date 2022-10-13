package com.example.treasuregame_back.GameUsers;

import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameRepository;
import com.example.treasuregame_back.game.GameService;
import com.example.treasuregame_back.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class GameUsersServiceTest {

    private GameUsersRepository mock;
    private GameUsersService service;

    @BeforeEach
    public void setUp() {
        mock = Mockito.mock(GameUsersRepository.class);
        service = new GameUsersService(mock);

    }

    @Test
    void searchGameUserMustRetundGameUser() {
        User user = new User(1L,"test","test@gmail.com");
        Game game = new Game("test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5,new GameUsers(user,123456));
        GameUsers gameUsers = new GameUsers(1L, game, user,123456);
        when(service.searchGameUser(game,user)).thenReturn(gameUsers);
        assertEquals(service.searchGameUser(game,user),gameUsers);
    }

    @Test
    void findInvitedUsersFromGameMustReturnListOfUsers() {
        User user = new User(1L,"test","test@gmail.com");
        User user2 = new User(2L,"test2","test2@gmail.com");
        Game game = new Game("test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5,new GameUsers(user, 123456),new GameUsers(user2, 234567));
        when(service.findInvitedUsersFromGame(game)).thenReturn(List.of(user,user2));
        assertEquals(service.findInvitedUsersFromGame(game).size(),2);
    }

    @Test
    void findGameUserById() {
        User user = new User(1L,"test","test@gmail.com");
        Game game = new Game("test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5,new GameUsers(user,123456));
        GameUsers gameUsers = new GameUsers(1L, game, user,123456);
        when(service.findGameUserById(1L)).thenReturn(gameUsers);
        assertEquals(service.findGameUserById(1L),gameUsers);
    }

    @Test
    void checkifexist() {
//        User user = new User(1L,"test","test@gmail.com");
//        Game game = new Game("test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5,new GameUsers(user,123456));
//        GameUsers gameUsers = new GameUsers(1L, game, user,123456);
//        when(service.checkifexist(game,user)).thenReturn(true);
//        when(service.checkifexist(new Game(),new User())).thenReturn(false);
//        doReturn(true).when(service.checkifexist(game,user));
//        doReturn(false).when(service.checkifexist(new Game(),new User()));
//        assertEquals(service.checkifexist(game,user),true);
//        assertEquals(service.checkifexist(new Game(),new User()),false);
    }
}