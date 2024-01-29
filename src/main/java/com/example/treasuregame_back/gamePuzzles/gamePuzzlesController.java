package com.example.treasuregame_back.gamePuzzles;

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

    @PostMapping(path = "/create")
    public void createNewGamePuzzle(@RequestBody gamePuzzles gamePuzzles){
        gamePuzzlesService.add(gamePuzzles);
    }
}
