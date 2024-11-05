package com.example.treasuregame_back.View;

import com.example.treasuregame_back.Services.GameUsersService;
import com.example.treasuregame_back.Services.GameService;
import com.example.treasuregame_back.Services.UserService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@Route(value = "TestView", layout = MainLayoutView.class)
@RolesAllowed("ADMIN")
public class TestView extends Div {
    @Autowired
    private GameService gameService;
    @Autowired
    private GameUsersService gameUsersService;
    @Autowired
    private UserService userService;

    public TestView() {

    }

}
