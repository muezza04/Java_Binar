package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.UsersRepository;
import org.binaracademy.challenge4.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> getAllUsers() {
        return Optional.ofNullable(usersRepository)
                .map(requestUsers -> usersRepository.readUsers())
                .orElse(null);
    }

    @Override
    public Boolean addNewUsers(Users users) {
        return Optional.ofNullable(users)
                   .map(newUsers -> usersRepository.save(users))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    public Users getUsersDetail(String users) {
        return usersRepository.findByUsername(users);
    }

    @Override
    @Transactional
    public Boolean updateUsers(Users users, String usernameId) {
        return Optional.ofNullable(users)
                .map(updateUsers -> usersRepository.updateUsers(users.getUsername(), users.getEmailAddress(), users.getPassword(), usernameId))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        usersRepository.deleteByUsername(username);
    }

    @Override
    public Users findUsername(String newUsername) {
        return usersRepository.findByUsername(newUsername);
//        return null;
    }
}