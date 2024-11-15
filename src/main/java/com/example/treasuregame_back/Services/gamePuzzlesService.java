package com.example.treasuregame_back.Services;

import com.example.treasuregame_back.Data.gamePuzzlesRepository;
import com.example.treasuregame_back.Models.Game;
import com.example.treasuregame_back.Models.gamePuzzles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;


@Service
public class gamePuzzlesService implements CrudListener<gamePuzzles> {

    private final com.example.treasuregame_back.Data.gamePuzzlesRepository gamePuzzlesRepository;

    @Autowired
    public gamePuzzlesService(gamePuzzlesRepository gamePuzzlesRepository) { this.gamePuzzlesRepository = gamePuzzlesRepository; }


    @Override
    public Collection<gamePuzzles> findAll() {
        return gamePuzzlesRepository.findAll();
    }

    @Override
    public gamePuzzles add(gamePuzzles gamePuzzles) {
        return gamePuzzlesRepository.save(gamePuzzles);
    }

    @Override
    public gamePuzzles update(gamePuzzles gamePuzzles) {
        return gamePuzzlesRepository.save(gamePuzzles);
    }

    @Override
    public void delete(gamePuzzles gamePuzzles) {
        gamePuzzlesRepository.delete(gamePuzzles);
    }

    public Collection<gamePuzzles> getGamePuzzleByGame(Game game){
        return gamePuzzlesRepository.findGamePuzzleByGame(game);
    }

    public Collection<gamePuzzles> getGamePuzzleByGameId(Long id){
        return gamePuzzlesRepository.findGamePuzzleByGameId(id);
    }
}
