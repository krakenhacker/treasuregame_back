package com.example.treasuregame_back.gamePuzzles;

import com.example.treasuregame_back.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path ="api/v1/gamepuzzles")
public class gamePuzzlesController {
    private final gamePuzzlesService gamePuzzlesService;

    @Autowired
    public gamePuzzlesController(gamePuzzlesService gamePuzzlesService) {
        this.gamePuzzlesService = gamePuzzlesService;
    }

    @GetMapping
    public Collection<gamePuzzles> getGamePuzzles() {
        return gamePuzzlesService.findAll();
    }

    @PostMapping(path = "/bygame")
    public Collection<gamePuzzles> getGamePuzzlesByGame(@RequestBody Game game){
        return gamePuzzlesService.getGamePuzzleByGame(game);
    }

    @GetMapping(path = "/bygameid/{id}")
    public Collection<gamePuzzles> getGamePuzzlesByGameId(@PathVariable("id") Long id){
        return gamePuzzlesService.getGamePuzzleByGameId(id);
    }

    @PostMapping(path = "/create")
    public void createNewGamePuzzle(@RequestBody gamePuzzles gamePuzzles){
        gamePuzzlesService.add(gamePuzzles);
    }
}
