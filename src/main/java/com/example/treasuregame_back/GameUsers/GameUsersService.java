package com.example.treasuregame_back.GameUsers;
import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;


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

    public GameUsers searchGameUser(Game game, User user){
        return gameUsersRepository.findGameUsersByGameAndUser(game,user);
    }

    public List<User> findInvitedUsersFromGame(Game game){
        return gameUsersRepository.findInvitedUsersFromGame(game);
    }

    public GameUsers findGameUserById(Long id){
        return gameUsersRepository.findGameUsersById(id);
    }
    public boolean checkifexist(Game game, User user){
        if(gameUsersRepository.findGameUsersByGameAndUser(game,user)==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkIfCodeIsUnique(int code){
        if(gameUsersRepository.findCode(code)==null){
            return true;
        }else{
            return false;
        }
    }
}
