package com.example.treasuregame_back.game;

import com.example.treasuregame_back.GameUsers.GameUsers;
import com.example.treasuregame_back.user.User;
import com.example.treasuregame_back.user.UserRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class GameConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner GameCommandLine(GameRepository repository){
        return args -> {
//            Game game1 = new Game("Test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5);
//            Game game2 = new Game("1T", LocalDateTime.now(),0.5,11.5,12.5,13.5,14.5);
//            List<Game> games = new ArrayList<>();
//            games.add(game1);
//            games.add(game2);
//            repository.saveAll(games);
            User user1 = new User("krakenhacker","george.sot@windowslive.com");
            User user2 = new User("garoufalis","george.sot19@gmail.com");
            userRepository.saveAll(Arrays.asList(user1,user2));
            repository.save(new Game("Test", LocalDateTime.now(), 0.5,10.5,11.5,12.5,13.5, new GameUsers(user1,"test"), new GameUsers(user2,"test")));
        };
    }
}
