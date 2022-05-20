package com.example.treasuregame_back.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class GameConfig {

    @Bean
    CommandLineRunner commandLineRunner(GameRepository repository){
        return args -> {
            Game game = new Game("Test", LocalDateTime.now(), LocalDateTime.now(),10.5,11.5,12.5,13.5);

            repository.saveAll(List.of(game));
        };
    }
}
