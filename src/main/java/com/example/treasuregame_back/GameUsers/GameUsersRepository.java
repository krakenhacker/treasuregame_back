package com.example.treasuregame_back.GameUsers;

import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GameUsersRepository extends JpaRepository<GameUsers, Long> {

    @Query("select gu from GameUsers gu where gu.id = ?1")
    GameUsers findGameUsersById(Long id);

    @Query("select gu from GameUsers gu,Game g,User u where u=gu.user and g=gu.game and gu.game=:game and gu.user=:user")
    GameUsers findGameUsersByGameAndUser(Game game, User user);

    @Query("select u from User u,GameUsers gu,Game g where u=gu.user and g=gu.game and gu.game=:game")
    List<User> findInvitedUsersFromGame(Game game);


}
