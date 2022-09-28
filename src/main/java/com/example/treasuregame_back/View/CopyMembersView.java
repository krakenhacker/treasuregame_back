package com.example.treasuregame_back.View;

import com.example.treasuregame_back.Email.EmailServiceImpl;
import com.example.treasuregame_back.GameUsers.GameUsers;
import com.example.treasuregame_back.GameUsers.GameUsersService;
import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.example.treasuregame_back.user.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;

@Route("copymembers")
@RolesAllowed("ADMIN")
public class CopyMembersView extends VerticalLayout {
    @Autowired
    private GameService gameservice;
    @Autowired
    private GameUsersService gameUsersService;
    @Autowired
    private EmailServiceImpl emailService;

    public CopyMembersView(GameService service){
        setupform(service);

    }

    public void setupform(GameService service){
        FormLayout formLayout = new FormLayout();
        ComboBox<Game> fromgame = new ComboBox<>("From");
        fromgame.setItems(service.findAll());
        fromgame.setItemLabelGenerator(Game::getName);

        ComboBox<Game> togame = new ComboBox<>("To");
        togame.setItems(service.findAll());
        togame.setItemLabelGenerator(Game::getName);
        add(new H1("Invite members from a game to another"));
        formLayout.add(fromgame,togame);

        add(formLayout,new Button("Copy", event ->{
            Game game1 = service.findGameById(fromgame.getValue().getId());
            List<User> users = gameUsersService.findInvitedUsersFromGame(game1);
            Game game2 = service.findGameById(togame.getValue().getId());
            int userssize = users.size();
            for(int i=0;i<userssize;i++) {
                GameUsers gameUsers = new GameUsers();
                gameUsers.setGame(game2);
                gameUsers.setUser(users.get(i));
                gameUsers.setCode(service.getRandomNumber());
                boolean flag = gameUsersService.checkifexist(game2, users.get(i));
                if (!flag) {
                    gameUsersService.update(gameUsers);
                    sendmail(game2,users.get(i));
                    Notification.show("invited member: "+users.get(i).getEmail()+"\nto game: "+game2.getName());
                }
            }
            })
        );
    }
    public void sendmail(Game game,User user){
            GameUsers gameUsers = gameUsersService.searchGameUser(game, user);
            String msgtext = String.format("You have been invited to game: " + game.getName() + "  starting at  " + game.getStart() + "  use invitation code:  " + gameUsers.getCode());
            emailService.sendSimpleMessage(user.getEmail(), "Treasure Game Invitation", msgtext);
    }
}
