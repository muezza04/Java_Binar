package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "username", length = 25, unique = true)
    private String username;

    @Column(name = "email_address", length = 50)
    private String emailAddress;

    @Column(name = "password", length = 25)
    private String password;

}
