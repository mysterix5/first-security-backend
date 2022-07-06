package de.neuefische.kanbanbackend.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDTO {

    private String username;
    private List<String> roles;

    public MyUserDTO(MyUser user) {
        username = user.getUsername();
        roles = user.getRoles();
    }
}
