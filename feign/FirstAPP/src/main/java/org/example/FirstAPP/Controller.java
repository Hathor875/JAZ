package org.example.FirstAPP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final HelloService helloService;

    @Autowired
    public Controller(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/test")
    public String getMessage() {
        return helloService.fetchHelloMessagePass();
    }
}
