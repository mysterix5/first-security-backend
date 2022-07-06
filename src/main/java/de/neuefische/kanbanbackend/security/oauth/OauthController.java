package de.neuefische.kanbanbackend.security.oauth;

import de.neuefische.kanbanbackend.security.JWTService;
import de.neuefische.kanbanbackend.model.security.LoginResponse;
import de.neuefische.kanbanbackend.model.user.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthController {
    private final OauthService oauthService;
    private final JWTService jwtService;

    @GetMapping
    public ResponseEntity<LoginResponse> githubOauthFlow(@RequestParam String code) {
        MyUser user;
        try {
            user = oauthService.githubOauthFlow(code);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles());
        return ResponseEntity.ok(new LoginResponse(jwtService.createToken(claims, user.getUsername())));
    }
}
