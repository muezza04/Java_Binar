package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.DTO.UsersResponse;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersResponse> getAllUsers() {
        log.info("Starting view get All users");
        return Optional.ofNullable(usersRepository)
                .map(requestUsers -> requestUsers.readUsers()
                        .stream()
                        .map(user -> UsersResponse.builder()
                                .username(user.getUsername())
                                .emailAddress(user.getEmailAddress())
                                .password(user.getPassword())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean addNewUsers(UsersResponse users) {
        log.info("Process Add new users");
        return Optional.ofNullable(users)
                   .map(newUsers -> UsersResponse.builder()
                           .username(newUsers.getUsername())
                           .emailAddress(newUsers.getEmailAddress())
                           .password(newUsers.getPassword())
                           .build())
                .map(user -> usersRepository.postUsers(String.valueOf(Users.builder().id(String.valueOf(UUID.randomUUID())).build()), user.getUsername(),user.getEmailAddress(),user.getPassword()))
                .map(obj -> true)
                .orElse(Boolean.FALSE);
    }

    @Override
    public UsersResponse getUsersDetail(String users) {
        log.info("Starting view get detail users individual");
        return Optional.ofNullable(usersRepository.findByUsername(users))
                .map(user -> UsersResponse.builder()
                        .username(user.getUsername())
                        .build())
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean updateUsers(UsersResponse users, String usernameId) {
        log.info("Starting update data users");
        return Optional.ofNullable(users)
                .map(updateUsers -> usersRepository.updateUsers(users.getUsername(), users.getEmailAddress(), users.getPassword(), usernameId))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        log.info("Starting delete data users");
        usersRepository.deleteByUsername(username);
    }

    @Override
    public UsersResponse findUsername(String newUsername) {
        log.info("Starting searching username");
        return Optional.ofNullable(usersRepository.findByUsername(newUsername))
                .map(user -> UsersResponse.builder()
                        .username(user.getUsername())
                        .build())
                .orElse(null);
    }

    //Error karena (Pageable) pageRequest
    @Override
    public List<Users> getPageable(Integer page) {
        return usersRepository.readUsersPage((Pageable) PageRequest.of(page, 2));
    }
}