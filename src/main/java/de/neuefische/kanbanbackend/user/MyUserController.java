package de.neuefische.kanbanbackend.user;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping
    public ResponseEntity<Void> postNewUser(@RequestBody UserCreationData userCreationData) {
        try {
            myUserService.createNewUser(userCreationData);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public String getUsername(Principal principal) {
        return principal.getName();
    }

}
