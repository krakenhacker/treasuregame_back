package com.example.treasuregame_back.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

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
    public boolean IfUserExist(User user){
        if(userRepository.findUserByEmail(user.getEmail())!=null) {
            return true;
        }
        else{
            return false;
        }
    }
}
