package com.example.treasuregame_back.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select g from Game g where g.name = ?1")
    Optional<Game> findGameByName(String name);
}
