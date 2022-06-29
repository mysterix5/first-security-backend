package de.neuefische.kanbanbackend.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

class MyUserServiceTest {

    @Test
    void shouldCreateUser() {
        // given
        MyUser newUser = new MyUser();
        newUser.setUsername("klausi");
        newUser.setPassword("manta");

        MyUser expectedUserToHaveBeenSaved = new MyUser();
        expectedUserToHaveBeenSaved.setUsername("klausi");
        expectedUserToHaveBeenSaved.setPassword("hashedManta");
        expectedUserToHaveBeenSaved.setRoles(List.of("user"));


        MyUserRepo userRepo = Mockito.mock(MyUserRepo.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(passwordEncoder.encode("manta")).thenReturn("hashedManta");

        MyUserService service = new MyUserService(userRepo, passwordEncoder);

        // when
        service.createNewUser(newUser);

        // then
        Mockito.verify(userRepo).save(expectedUserToHaveBeenSaved);
    }

}