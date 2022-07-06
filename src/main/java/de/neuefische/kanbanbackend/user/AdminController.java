package de.neuefische.kanbanbackend.user;

import de.neuefische.kanbanbackend.model.user.MyUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MyUserService myUserService;

    @PutMapping("/{username}/{role}")
    public void addNewRole(@PathVariable String username, @PathVariable String role) {
        myUserService.findByUsername(username)
                .map(userFromDB -> {
                    List<String> roles = userFromDB.getRoles();
                    roles.add(role);
                    userFromDB.setRoles(roles);
                    return userFromDB;
                })
                .map(myUserService::saveUser);
    }

    @DeleteMapping("/{username}/{role}")
    public void deleteRole(@PathVariable String username, @PathVariable String role) {
        myUserService.findByUsername(username)
                .map(userFromDB -> {
                    List<String> roles = userFromDB.getRoles();
                    roles.remove(role);
                    userFromDB.setRoles(roles);
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
        return myUserService.getAllUsers().stream().map(MyUserDTO::new).toList();
    }

}
