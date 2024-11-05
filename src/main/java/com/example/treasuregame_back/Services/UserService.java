package com.example.treasuregame_back.Services;
import com.example.treasuregame_back.Data.UserRepository;
import com.example.treasuregame_back.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
public class UserService  implements CrudListener<User> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User saveUserIfnotExist(User user){
        if(userRepository.findUserByEmail(user.getEmail())!=null) {
            user = userRepository.findUserByEmail(user.getEmail());
            return user;
        }
        else{
            userRepository.save(user);
            user = userRepository.findUserByEmail(user.getEmail());
            return user;
        }
    }
}
