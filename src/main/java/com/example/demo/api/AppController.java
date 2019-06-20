package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AppService;

@RestController("AppControllerV1")
@RequestMapping("/apps")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendSimpleMessageToSlack (
            @PathVariable(name = "message") String message) {
        appService.sendMessageToSlack(message);
        return ResponseEntity.ok(message);
    }

}
