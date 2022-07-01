package de.neuefische.kanbanbackend;

import de.neuefische.kanbanbackend.user.LoginData;
import de.neuefische.kanbanbackend.user.LoginResponse;
import de.neuefische.kanbanbackend.user.UserCreationData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KanbanBackendApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void secureIntegrationTest() {
        // create new user
        var newUser = new UserCreationData();
        newUser.setUsername("klausi");
        newUser.setPassword("manta");
        newUser.setPasswordAgain("manta");
        ResponseEntity<Void> createUserResponse = restTemplate.postForEntity("/api/user", newUser, Void.class);
        Assertions.assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // failed login
        ResponseEntity<LoginResponse> failedLoginResponse = restTemplate.postForEntity("/api/login", new LoginData("klausi", "opel"), LoginResponse.class);
        Assertions.assertThat(failedLoginResponse.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        // login
        ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity("/api/login", new LoginData("klausi", "manta"), LoginResponse.class);
        String jwt = loginResponse.getBody().getToken();

        Assertions.assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(jwt).isNotBlank();

        ResponseEntity<String> getResponse = restTemplate.exchange(
                "/api/user",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(jwt)),
                String.class
        );
        Assertions.assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getResponse.getBody()).isEqualTo("klausi");
    }

    private HttpHeaders createHeaders(String jwt) {
        String authHeaderValue = "Bearer " + jwt;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeaderValue);
        return headers;
    }

}
