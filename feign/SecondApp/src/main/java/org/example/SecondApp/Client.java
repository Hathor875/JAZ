package org.example.SecondApp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "HelloService", url = "http://localhost:8080")
public interface Client {
    @GetMapping("/helloPass")
    String getHelloMessagePass();

}
