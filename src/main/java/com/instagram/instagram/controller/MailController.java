package com.instagram.instagram.controller;

import com.instagram.instagram.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService notificationSenderService;

    @GetMapping
    public String sendNotification(@RequestParam String username) {

        notificationSenderService.send(username);

        return "Notification successfully sent to " + username + " !!!";
    }
    @PostMapping
    public String sendActivationLink(
            @RequestParam String username,
            @RequestParam String email){

        notificationSenderService.sendActivationLink(username, email);

        return "Notification successfully sent to " + username + " !!!";
    }
    @PostMapping("/report")
    public String sendActivationLink(){

        notificationSenderService.sendWeeklyReport(null,"email");

        return "Notification successfully sent to " + null + " !!!";
    }

}
