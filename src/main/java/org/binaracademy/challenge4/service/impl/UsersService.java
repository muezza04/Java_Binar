package org.binaracademy.challenge4.service.impl;

import org.binaracademy.challenge4.model.DTO.response.UsersResponse;
import org.binaracademy.challenge4.model.Users;

import java.util.List;

public interface UsersService {

    //Optional
    List<UsersResponse> getAllUsers();

    Boolean addNewUsers(Users users);
    UsersResponse getUsersDetail(String users);
    Boolean updateUsers(Users users, String usernameId);
    void deleteByUsername(String username);

    Users findUsername(String newUsername);

    List<Users> getPageable(Integer page);
}
