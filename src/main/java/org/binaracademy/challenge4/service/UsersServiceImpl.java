package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.DTO.response.UsersResponse;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.UsersRepository;
import org.binaracademy.challenge4.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersResponse> getAllUsers() {
        return Optional.ofNullable(usersRepository)
                .map(requestUsers -> requestUsers.readUsers()
                        .stream()
                        .map(user -> UsersResponse.builder()
                                .usersUsername(user.getUsername())
                                .usersEmail(user.getEmailAddress())
                                .usersPassword(user.getPassword())
                                .build())
                        .collect(Collectors.toList()))
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
    public UsersResponse getUsersDetail(String users) {
        return Optional.ofNullable(usersRepository.findByUsername(users))
                .map(user -> UsersResponse.builder()
                        .usersUsername(user.getUsername())
                        .build())
                .orElse(null);
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

    //Error karena (Pageable) pageRequest
    @Override
    public List<Users> getPageable(Integer page) {
        return usersRepository.readUsersPage((Pageable) PageRequest.of(page, 2));
    }
}