package de.neuefische.kanbanbackend.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginData {

    private String username;
    private String password;

}
