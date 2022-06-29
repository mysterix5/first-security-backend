package de.neuefische.kanbanbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepo myUserRepo;
    private final PasswordEncoder encoder;

    public void createNewUser(MyUser newUser) {
        String encodedPassword = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setRoles(Collections.singletonList("user"));
        myUserRepo.save(newUser);
    }


    public Optional<MyUser> findByUsername(String username) {
        return myUserRepo.findByUsername(username);
    }

    public MyUser saveUser(MyUser user) {
        return myUserRepo.save(user);
    }

    public List<MyUser> getAllUsers() {
        return myUserRepo.findAll();
    }

    public void deleteByUsername(String username) {
        myUserRepo.deleteByUsername(username);
    }
}
