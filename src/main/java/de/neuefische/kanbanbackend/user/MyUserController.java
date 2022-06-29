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
    public void postNewUser(@RequestBody MyUser newUser) {
        myUserService.createNewUser(newUser);
    }


    @GetMapping
    public String getUsername(Principal principal) {
        return principal.getName();
    }


}
