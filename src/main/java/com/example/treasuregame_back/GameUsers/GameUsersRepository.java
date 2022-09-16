package com.example.treasuregame_back.GameUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUsersRepository extends JpaRepository<GameUsers, Long> {

    @Query("select gu from GameUsers gu where gu.id = ?1")
    GameUsers findGameUsersById(Long id);

    @Query("select gu from GameUsers gu where gu.game.id = ?1 and gu.user.id = ?2")
    GameUsers findGameUsersCodeByGameIdAndUserId(Long game,Long user);
}
