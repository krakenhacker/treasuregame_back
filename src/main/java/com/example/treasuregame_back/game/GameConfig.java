package com.example.treasuregame_back.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class GameConfig {

    @Bean
    CommandLineRunner commandLineRunner(GameRepository repository){
        return args -> {
            Game game1 = new Game("Test", LocalDate.now(), LocalTime.now(), 0.5,10.5,11.5,12.5,13.5);
            Game game2 = new Game("1T", LocalDate.now(), LocalTime.now(),0.5,11.5,12.5,13.5,14.5);
            List<Game> games = new ArrayList<>();
            games.add(game1);
            games.add(game2);
            repository.saveAll(games);
        };
    }
}
