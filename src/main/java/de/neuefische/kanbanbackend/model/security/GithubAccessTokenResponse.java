package de.neuefische.kanbanbackend.model.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubAccessTokenResponse {
    @JsonProperty("access_token")
    private String token;
}
