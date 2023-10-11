package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Users;

import java.util.List;

public interface UsersService {

    List<Users> getAllUsers();
    Boolean addNewUsers(Users users);
    Users getUsersDetail(String users);
}
