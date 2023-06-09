package com.instagram.instagram.controller;

import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService notificationSenderService;


    @PostMapping
    public String resendActivation(
            @RequestParam String username,
            @RequestParam String email){

        notificationSenderService.resendActivationLink(username, email);

        return "Notification successfully sent to " + username + " !!!";
    }


}
