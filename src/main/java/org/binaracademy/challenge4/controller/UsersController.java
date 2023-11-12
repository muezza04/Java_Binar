package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.DTO.response.UsersResponse;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class UsersController {

    @Autowired
    private UsersService usersService;

    public Scanner scanner = new Scanner(System.in);

    public void showMenuUsers() {
        while (true) {
            System.out.println("Berikut tampilan semua akun Users");
            System.out.println("Username \t | \t Email \t | \t Password");
            usersService.getAllUsers().forEach(users -> {
                System.out.println(
                        users.getUsersUsername() + "\t - \t" +
                                users.getUsersEmail() + "\t - \t" + users.getUsersPassword());
            });

            System.out.println("1. Detail account");
            System.out.println("2. Add users");
            System.out.println("3. Update users");
            System.out.println("4. Delete account");
//            System.out.println("5. Page All user");
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
                    case 5:
//                        this.showPageUsers(null);
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

        UsersResponse user = usersService.getUsersDetail(userDetail);
        System.out.println("\nUsername : \t" + user.getUsersUsername());
        System.out.println("Email Address : " + user.getUsersEmail());
        System.out.println("Password : \t" + user.getUsersPassword() + "\n");

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

    public void showPageUsers(List<Users> userPage) {
        System.out.println("Berikut tampilan semua akun Users");
        System.out.println("Username \t | \t Email \t | \t Password");
        userPage = Optional.ofNullable(userPage)
                        .orElseGet(() -> usersService.getPageable(0));

        userPage.forEach(users -> {
            System.out.println(
                    users.getUsername() + "\t - \t" +
                            users.getEmailAddress() + "\t - \t" + users.getPassword());
        });

        System.out.print("Masukan no halaman yang ingin anda tuju, Ketik \"n\" jika anda ingin keluar => ");
        try {
            int choose = scanner.nextInt();
            scanner.nextLine();
            userPage = usersService.getPageable(choose);
            this.showPageUsers(userPage);
        } catch (InputMismatchException e) {
            this.showMenuUsers();
        }
    }
}
