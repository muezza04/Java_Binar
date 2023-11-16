package org.binaracademy.challenge4.service.impl;

import org.binaracademy.challenge4.model.DTO.UsersResponse;
import org.binaracademy.challenge4.model.Users;

import java.util.List;

public interface UsersService {

    //Optional
    List<UsersResponse> getAllUsers();

    Boolean addNewUsers(UsersResponse users);
    UsersResponse getUsersDetail(String users);
    Boolean updateUsers(UsersResponse users, String usernameId);
    void deleteByUsername(String username);

    UsersResponse findUsername(String newUsername);

    List<Users> getPageable(Integer page);
}
