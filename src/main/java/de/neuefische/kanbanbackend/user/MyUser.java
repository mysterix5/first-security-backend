package de.neuefische.kanbanbackend.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    @Id
    private String id;

    private String username;
    private String password;
    private List<String> roles;
}
