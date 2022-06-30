package de.neuefische.kanbanbackend.user.oauth;

import lombok.Data;

@Data
public class GithubUser {
    private long id;
    private String Login;
}
