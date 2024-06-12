package org.example.FirstAPP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private final Client client;

    @Autowired
    public HelloService(Client client) {
        this.client = client;
    }

    public String fetchHelloMessagePass() {
        try {
            String response = client.getMessage();
            if ("Hi".equals(response)) {
                String goodResponseMessage = "Good response: ";
                return goodResponseMessage + response;
            } else {
                return "Bad response: ";
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }

}
