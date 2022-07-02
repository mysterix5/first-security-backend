package de.neuefische.kanbanbackend.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactRouterFix {

    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String forwardToRouteUrl() {
        return "forward:/";
    }
}