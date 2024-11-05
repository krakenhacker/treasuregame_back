package com.example.treasuregame_back.Data;

import com.example.treasuregame_back.Models.Game;
import com.example.treasuregame_back.Models.gamePuzzles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface gamePuzzlesRepository extends JpaRepository<gamePuzzles, Long> {

    @Query("select gp from gamePuzzles gp where gp.game = :game")
    Collection<gamePuzzles> findGamePuzzleByGame(Game game);

    @Query("select gp from gamePuzzles gp where gp.game.id= ?1")
    Collection<gamePuzzles> findGamePuzzleByGameId(Long id);



//    @Query("select gu from GameUsers gu,Game g,User u where u=gu.user and g=gu.game and gu.game=:game and gu.user=:user")
//    GameUsers findGameUsersByGameAndUser(Game game, User user);
//
//    @Query("select u from User u,GameUsers gu,Game g where u=gu.user and g=gu.game and gu.game=:game")
//    List<User> findInvitedUsersFromGame(Game game);
//
//    @Query("select gu.code from GameUsers gu where gu.code=:code")
//    Integer findCode(int code);


}
