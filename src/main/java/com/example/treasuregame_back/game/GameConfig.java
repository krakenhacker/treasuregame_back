package com.example.treasuregame_back.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GameConfig {

    @Bean
    CommandLineRunner GameCommandLine(GameRepository repository){
        return args -> {
            Game game1 = new Game("Test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5);
            Game game2 = new Game("1T", LocalDateTime.now(),0.5,11.5,12.5,13.5,14.5);
            List<Game> games = new ArrayList<>();
            games.add(game1);
            games.add(game2);
            repository.saveAll(games);
        };
    }
}
