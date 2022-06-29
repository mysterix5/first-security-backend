package de.neuefische.kanbanbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepo myUserRepo;
    private final PasswordEncoder encoder;

    public void createNewUser(UserCreationData userCreationData) {
        if (!Objects.equals(userCreationData.getPassword(), userCreationData.getPasswordAgain())) {
            throw new IllegalArgumentException("password do not match");
        }

        String encodedPassword = encoder.encode(userCreationData.getPassword());
        MyUser newUser = new MyUser();
        newUser.setUsername(userCreationData.getUsername());
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
}
