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

    public Optional<MyUserDTO> findByName(String username) {
        Optional<MyUser> optUser = myUserRepo.findByUsername(username);
        if (optUser.isPresent()){
            return Optional.of(new MyUserDTO(optUser.get().getUsername()));
        }
        return Optional.empty();
    }
}
