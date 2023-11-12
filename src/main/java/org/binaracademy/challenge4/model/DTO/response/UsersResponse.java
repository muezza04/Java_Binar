package org.binaracademy.challenge4.model.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {

    private String usersUsername;
    private String usersEmail;
    private String usersPassword;

}
