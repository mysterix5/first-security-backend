package de.neuefische.kanbanbackend.user;


import de.neuefische.kanbanbackend.user.oauth.GithubUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
public class MyUser {

    @Id
    private String id;
    private long githubUserId;
    private String username;
    private String password;
    private List<String> roles;

    public MyUser(GithubUser githubUser){
        githubUserId = githubUser.getId();
        username = githubUser.getLogin();
        roles = List.of("user");
    }
}
