package de.neuefische.kanbanbackend.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginData {

    private String username;
    private String password;

}
