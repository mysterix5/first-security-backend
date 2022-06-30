package de.neuefische.kanbanbackend.user.oauth;

import de.neuefische.kanbanbackend.user.MyUser;
import de.neuefische.kanbanbackend.user.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final RestTemplate template;
    private final MyUserService myUserService;
    @Value("${GITHUB_CLIENT_ID}")
    private String githubClientId;
    @Value("${GITHUB_CLIENT_SECRET}")
    private String githubClientSecret;

    public MyUser githubOauthFlow(String code) {
        String githubAuthorizationToken = getAuthorizationTokenFromGithub(code).getToken();
        System.out.println("github authorization token: " + githubAuthorizationToken);

        GithubUser githubUser = getUserFromGithubAccessToken(githubAuthorizationToken);

        System.out.println(githubUser);

//        MyUser user = myUserService.createOrGet(gitHubUser.getBody());

        return myUserService.createOrGetUserFromMongoDB(githubUser);

    }

    private GithubAccessTokenResponse getAuthorizationTokenFromGithub(String code) {
        String url = "https://github.com/login/oauth/access_token?"
                + "client_id=" + githubClientId
                + "&client_secret=" + githubClientSecret
                + "&code=" + code;
        System.out.println(url);
        ResponseEntity<GithubAccessTokenResponse> request = template.postForEntity(url, null, GithubAccessTokenResponse.class);
        return request.getBody();
    }

    private GithubUser getUserFromGithubAccessToken(String githubAccessToken){
        ResponseEntity<GithubUser> gitHubUser = template.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                new HttpEntity<>(
                        new HttpHeaders() {{
                    String authHeader = "Bearer " + githubAccessToken;
                    set( "Authorization", authHeader );
                }}),
                GithubUser.class
        );

        return gitHubUser.getBody();
    }


}
