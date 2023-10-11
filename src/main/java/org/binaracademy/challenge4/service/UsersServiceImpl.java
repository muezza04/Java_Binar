package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> getAllUsers() {
        return Optional.ofNullable(usersRepository)
                .map(requestUsers -> usersRepository.findAll())
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

}
