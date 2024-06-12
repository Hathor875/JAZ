package org.example.FirstAPP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FirstAPP {

    public static void main(String[] args) {
        SpringApplication.run(FirstAPP.class, args);
    }
}
