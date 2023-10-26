package com.brunin.api.rest.open;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open")
public class ServerStatusRestController {

    @GetMapping("/status")
    public String getStatus() {
        return "online";
    }
}
