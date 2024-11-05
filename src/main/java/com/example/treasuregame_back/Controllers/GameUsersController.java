package com.example.treasuregame_back.Controllers;

import com.example.treasuregame_back.Services.GameUsersService;
import com.example.treasuregame_back.Models.Game;
import com.example.treasuregame_back.Models.GameUsers;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path ="api/v1/gamepusers")
public class GameUsersController {
    private final GameUsersService gameUsersService;

    public GameUsersController(GameUsersService gameUsersService) {
        this.gameUsersService = gameUsersService;
    }

    @GetMapping
    public Collection<GameUsers> getGamesUsers(){
        return gameUsersService.findAll();
    }

    @GetMapping(path = "/code/{code}")
    public Game getGameByCode(@PathVariable("code") int code){
        return gameUsersService.findGameByCode(code);
    }
}
