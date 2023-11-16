package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.DTO.UsersResponse;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/all-users")
    public List<UsersResponse> getAllUsers() {
        log.info("getting all users in controller");
        return usersService.getAllUsers();
    }

    @PostMapping(value = "/add-users")
    public ResponseEntity<String> postNewUsers(@RequestBody UsersResponse users) {
      try {
          usersService.addNewUsers(users);
          return new ResponseEntity<>("Users Created success", HttpStatus.CREATED);
      } catch (RuntimeException e) {
          return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("/{username}")
    public ResponseEntity<UsersResponse> getByUsername(@PathVariable String username) {
        UsersResponse user = usersService.findUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update-users/{username}")
    public ResponseEntity<String> putUsers(@PathVariable String username, @RequestBody UsersResponse users){
        try {
            usersService.updateUsers(users, username);
            return new ResponseEntity<>("Username " + username + " update successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete-users/{username}")
    public ResponseEntity<String> deleteUsers(@PathVariable String username){
        try {
            usersService.deleteByUsername(username);
            return new ResponseEntity<>("Username " + username + " delete successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
