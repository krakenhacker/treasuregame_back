package com.example.treasuregame_back.GameUsers;
import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;


@Service
public class GameUsersService implements CrudListener<GameUsers> {

    private final GameUsersRepository gameUsersRepository;

    @Autowired
    public GameUsersService(GameUsersRepository gameUsersRepository) { this.gameUsersRepository = gameUsersRepository; }


    @Override
    public Collection<GameUsers> findAll() { return gameUsersRepository.findAll(); }

    @Override
    public GameUsers add(GameUsers gameUsers) {
        return gameUsersRepository.save(gameUsers);
    }

    @Override
    public GameUsers update(GameUsers gameUsers) {
        return gameUsersRepository.save(gameUsers);
    }

    @Override
    public void delete(GameUsers gameUsers) {
        gameUsersRepository.delete(gameUsers);
    }

    public GameUsers searchCode(Game game, User user){
        return gameUsersRepository.findGameUsersCodeByGameandUser(game,user);
    }
}
