package com.example.treasuregame_back.game;

import com.example.treasuregame_back.GameUsers.GameUsers;
import com.example.treasuregame_back.user.User;
import com.example.treasuregame_back.user.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
public class GameService  implements CrudListener<Game> {
    private final GameRepository gameRepository;
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


//    public List<Game> getGames(){
//        return gameRepository.findAll();
//    }
//
//    public void addNewGame(Game game) {
//        gameRepository.save(game);
//    }
//
    @Override
    public Collection<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game add(Game game) {
        return gameRepository.save(game);
    }

    public Game addGameWithUser(Game game, User user){
        return gameRepository.save(new Game(game.getName(),game.getStart(),game.getDuration(), game.getX(), game.getY(), game.getZ(), game.getW(),new GameUsers(user,"test")));
    }

    @Override
    public Game update(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void delete(Game game) {
        gameRepository.delete(game);
    }
}
