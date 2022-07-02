package de.neuefische.kanbanbackend.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ReactRouterFix {
//    @RequestMapping(value = "/**/{[path:[^\\.]*}")
//    public String forwardToRouteUrl() {
//        return "forward:/";
//    }
    @RequestMapping(value = "/oauth")
    public String forwardToOauth() {
        return "forward:/";
    }
}