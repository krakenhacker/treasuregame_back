package com.example.treasuregame_back.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
class UserServiceTest {
    @Autowired
    private UserRepository mock;
    @Autowired
    private UserService userService;
    private User user,returneduser;

    @BeforeEach
    void setUp() {
        mock = Mockito.mock(UserRepository.class);
        userService = new UserService(mock);
        user = new User(1L,"krakenhacker","george.sot@windowslive.com");
        returneduser = new User();
    }

    @Test
    void findUserByEmailMustReturnaUser() {
        when(userService.findUserByEmail("george.sot@windowslive.com")).thenReturn(user);
        returneduser = userService.findUserByEmail("george.sot@windowslive.com");
        assertEquals(returneduser,user);
    }

    @Test
    void SaveUserOnRepositoryIfnotAlreadyExist() {
        when(userService.saveUserIfnotExist(returneduser)).thenReturn(user);
        returneduser = userService.saveUserIfnotExist(returneduser);
        assertEquals(returneduser,user);
    }

    @Test
    void findAllMustReturnAllUsers(){
        Collection<User> users = new ArrayList<>();
        users.add(user);
        users.add(new User(2L,"test@gmail.com","test"));
        when(userService.findAll()).thenReturn(users);
        assertEquals(2,userService.findAll().size());
    }


}