package com.example.treasuregame_back.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
class GameServiceTest {
        private GameRepository mock;
        private GameService service;

        @BeforeEach
        public void setUp() {
            mock = Mockito.mock(GameRepository.class);
            service = new GameService(mock);

        }

        LocalDateTime DateStartTest = LocalDateTime.of(2000, Month.JANUARY,1,12,00);
        LocalTime TimeStartTest = LocalTime.of(12, 15);

        @Test
        void getGamesMustReturnsAtLeasteOneGame() {
                Collection<Game> games = new ArrayList<>();
                Game game = new Game("test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5);
                games.add(game);
                when(service.findAll()).thenReturn(games);
                games = service.findAll();
                assertNotEquals(0, games.size());
        }
        @Test
        void AddNewGameMethodSuccesfullyAddANewGameIntoRepository() {
                Game game = new Game("test", DateStartTest,0.5,10.5,11.5,12.5,13.5);
                service.add(game);
                when(mock.findGameByName("test")).thenReturn(game);
                game = mock.findGameByName("test");
                assertEquals("test", game.getName());
                assertEquals(DateStartTest,game.getStart());
                assertEquals(0.5,game.getDuration());
                assertEquals(10.5,game.getX());
                assertEquals(11.5,game.getY());
                assertEquals(12.5,game.getZ());
                assertEquals(13.5,game.getW());



        }
    }