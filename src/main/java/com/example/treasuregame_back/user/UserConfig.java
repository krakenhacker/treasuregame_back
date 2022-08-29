package com.example.treasuregame_back.user;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner UserCommandLine(UserRepository repository){
        return args -> {
            User user1 = new User("krakenhacker","george.sot@windowslive.com","12345");
            User user2 = new User("garoufalis","george.sot19@gmail.com","56789");
            List<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);
            repository.saveAll(users);
        };
    }
}
