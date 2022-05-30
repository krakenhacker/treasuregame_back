package com.example.treasuregame_back.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GameControllerTest {

    @AutoConfigureMockMvc

    class AudiodbServiceTest {
        private GameRepository mock;
        private GameService service;

        @BeforeEach
        public void setUp()
        {
            mock = Mockito.mock(GameRepository.class);
            service = new GameService(mock);
        }

        @Test
    void getGames() {
            List<Game> game = service.getGames();
            assertEquals(1, game.size());
    }

    @Test
    void createNewGame() {
    }
}
}