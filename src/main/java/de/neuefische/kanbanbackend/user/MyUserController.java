package de.neuefische.kanbanbackend.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MyUserDTO> findUserByUsername(@PathVariable String username){
        return ResponseEntity.of(myUserService.findByName(username));
    }


}
