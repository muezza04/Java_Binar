package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class UsersController {

//    @Autowired
//    private MenuMainController menuMainController;

    @Autowired
    private UsersService usersService;

    public Scanner scanner = new Scanner(System.in);

    public void showMenuUsers() {
        while (true) {
            System.out.println("Berikut tampilan semua akun Users");
            System.out.println("Username \t | \t Email \t | \t Password");
            usersService.getAllUsers().forEach(users -> {
                System.out.println(
                        users.getUsername() + "\t - \t" +
                                users.getEmailAddress() + "\t - \t" + users.getPassword());
            });

            System.out.println("1. Detail account");
            System.out.println("2. Add users");
            System.out.println("3. Update users");
            System.out.println("4. Delete account");
            System.out.println("99. Back menu main");
            System.out.println("0. Out");

            System.out.print("=> ");
            try {
                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                    this.showDetailUsers();
                        break;
                    case 2:
                        this.showAddUsers();
                        break;
                    case 3:
                        this.showUpdateUsers();
                        break;
                    case 4:
                        this.showDeleteUsers();
                        break;
                    case 99:
//                        menuMainController.showMenuMain();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Input Failed...");
                scanner.nextLine();
            }
        }
    }

    public void showAddUsers() {
        System.out.println("\n=====New Users=====");
        System.out.print("Username : ");
        String newUsername = scanner.nextLine();
        System.out.print("Email Address : ");
        String newEmail = scanner.nextLine();
        System.out.print("Password : ");
        String newPassword = scanner.nextLine();

        Users newUser = Users.builder()
                .username(newUsername)
                .emailAddress(newEmail)
                .password(newPassword)
                .build();

        usersService.addNewUsers(newUser);
        System.out.println("Users New in Add!\n");
        this.showMenuUsers();
    }

    public void showDetailUsers() {
        System.out.print("Name username : ");
        String userDetail = scanner.nextLine();

        Users user = usersService.getUsersDetail(userDetail);
        System.out.println("\nUsername : \t" + user.getUsername());
        System.out.println("Email Address : " + user.getEmailAddress());
        System.out.println("Password : \t" + user.getPassword() + "\n");

        this.showMenuUsers();
    }

    public void showUpdateUsers() {
        System.out.println("\n=====Update Users=====");
        System.out.print("Name username which will update : ");
        String usernameId = scanner.nextLine();
        System.out.print("update Username : ");
        String updateUsername = scanner.nextLine();
        System.out.print("update Email Address : ");
        String updateEmail = scanner.nextLine();
        System.out.print("update password : ");
        String updatePassword = scanner.nextLine();

        Users updateUser = Users.builder()
                .username(updateUsername)
                .emailAddress(updateEmail)
                .password(updatePassword)
                .build();

        usersService.updateUsers(updateUser ,usernameId);
        System.out.println("Users update success!\n");
        this.showMenuUsers();
    }

    public void showDeleteUsers() {
        System.out.print("Username which is delete : ");
        String userDelete = scanner.nextLine();

        usersService.deleteByUsername(userDelete);
        System.out.println("User delete is success!\n");
        this.showMenuUsers();
    }
}
