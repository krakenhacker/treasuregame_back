package com.example.treasuregame_back.game;

import com.example.treasuregame_back.Data.GameRepository;
import com.example.treasuregame_back.Models.GameUsers;
import com.example.treasuregame_back.Models.Game;
import com.example.treasuregame_back.Services.GameService;
import com.example.treasuregame_back.Models.User;
import com.example.treasuregame_back.Data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDateTime;

import java.util.Arrays;

@Configuration
public class GameConfig {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameService gameService;

    @Bean
    CommandLineRunner GameCommandLine(GameRepository repository){
        return args -> {
            User user1 = new User("krakenhacker","george.sot@windowslive.com");
            User user2 = new User("garoufalis","george.sot19@gmail.com");
            userRepository.saveAll(Arrays.asList(user1,user2));
            Game game1 = new Game("Test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5, new GameUsers(user1, gameService.getRandomNumber()), new GameUsers(user2,gameService.getRandomNumber()));
            Game game2 = new Game("Testcopy", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5);
            repository.save(game1);
            gameService.add(game2);
        };
    }
}
