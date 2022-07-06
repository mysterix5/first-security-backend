package de.neuefische.kanbanbackend.user;

import de.neuefische.kanbanbackend.model.user.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MyUserRepo extends MongoRepository<MyUser, String> {

    Optional<MyUser> findByUsername(String username);
    Optional<MyUser> findByGithubUserId(long githubUserId);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}
