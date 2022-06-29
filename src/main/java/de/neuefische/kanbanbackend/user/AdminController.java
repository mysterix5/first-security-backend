package de.neuefische.kanbanbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MyUserService myUserService;

    @PutMapping("/{username}")
    public void giveAdminRights(@PathVariable String username) {
        myUserService.findByUsername(username)
                .map(userFromDB -> {
                    userFromDB.setRoles(List.of("admin"));
                    return userFromDB;
                })
                .map(myUserService::saveUser);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        myUserService.deleteByUsername(username);
    }

    @GetMapping
    public List<MyUserDTO> getAllUsers(){
        return myUserService.getAllUsers().stream().map(u->new MyUserDTO(u)).toList();
    }

}
