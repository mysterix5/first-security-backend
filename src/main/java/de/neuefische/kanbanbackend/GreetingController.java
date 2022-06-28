package de.neuefische.kanbanbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/greet")
public class GreetingController {



    @GetMapping
    public String sayHello(Principal principal){
        return principal.getName();
    }


}
