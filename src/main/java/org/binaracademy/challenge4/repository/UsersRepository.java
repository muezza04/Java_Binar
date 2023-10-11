package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Users findByUsername(String username);
}
