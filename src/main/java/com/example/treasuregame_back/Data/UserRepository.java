package com.example.treasuregame_back.Data;
import com.example.treasuregame_back.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.nickname = ?1")
    User findUserByName(String nickname);
    @Query("select u from User u where u.id = ?1")
    User findUserById(int id);
    @Query("select u from User u where u.email = ?1")
    User findUserByEmail(String email);
}
