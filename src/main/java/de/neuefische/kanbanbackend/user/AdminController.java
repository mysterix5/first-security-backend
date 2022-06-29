package de.neuefische.kanbanbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
