package com.example.treasuregame_back.GameUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUsersRepository extends JpaRepository<GameUsers, Long> {

    @Query("select gu from GameUsers gu where gu.id = ?1")
    GameUsers findGameUsersById(Long id);

    @Query("select gu from GameUsers gu,Game g,User u where u=gu.user and g=gu.game and u.id=:userid and g.id=:gameid")
    GameUsers findGameUsersCodeByGameIdAndUserId(Long userid,Long gameid);
}
