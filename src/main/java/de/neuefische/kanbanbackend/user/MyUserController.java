package de.neuefische.kanbanbackend.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping
    public void postNewUser(@RequestBody MyUser newUser){
        myUserService.createNewUser(newUser);
    }


    @GetMapping("/{username}")
    public Optional<MyUser> findUserByUsername(@PathVariable String username){
        return myUserService.findByName(username);
    }


}
