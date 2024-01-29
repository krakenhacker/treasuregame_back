package com.example.treasuregame_back.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path ="api/v1/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public Collection<Game> getGames(){
        return gameService.findAll();
    }

    @PostMapping(path = "/create")
    public void createNewGame(@RequestBody Game game){
        gameService.add(game);
    }


}
