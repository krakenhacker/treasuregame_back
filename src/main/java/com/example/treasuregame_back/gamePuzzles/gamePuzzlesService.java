package com.example.treasuregame_back.gamePuzzles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;


@Service
public class gamePuzzlesService implements CrudListener<gamePuzzles> {

    private final gamePuzzlesRepository gamePuzzlesRepository;

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
}
