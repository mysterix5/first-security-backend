package de.neuefische.kanbanbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepo myUserRepo;

    public void createNewUser(MyUser newUser) {
        myUserRepo.save(newUser);
    }

    public Optional<MyUser> findByName(String username) {
        return myUserRepo.findByUsername(username);
    }
}
