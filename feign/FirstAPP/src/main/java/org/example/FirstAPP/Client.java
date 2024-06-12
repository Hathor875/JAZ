package org.example.FirstAPP;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "HelloService", url = "http://localhost:50000")
public interface Client {
    @GetMapping("/hello")
    String getMessage();
}
