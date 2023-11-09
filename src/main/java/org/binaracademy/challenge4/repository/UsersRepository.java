package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Users findByUsername(String username);
    @Query(nativeQuery = true, value = "select u.user_id, u.username, u.email_address, u.password from users u")
    List<Users> readUsers();

    @Modifying
    @Query(nativeQuery = true, value = "update users set username= :username , email_address= :emailAddress, password= :password where username = :usernameId")
    Integer updateUsers(@Param("username") String username, @Param("emailAddress") String emailAddress, @Param("password") String password, @Param("usernameId") String usernameId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from users u where u.username = :username")
    Integer deleteByUsername(@Param("username") String username);
}
