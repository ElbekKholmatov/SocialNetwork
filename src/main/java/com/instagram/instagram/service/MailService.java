package com.instagram.instagram.service;

import com.instagram.instagram.domains.VerificationCode;
import com.instagram.instagram.repository.VerificationRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final VerificationRepository verificationRepository;

    @Async
    public void sendActivationLink(String username, String email) {
        try {

            String otp = String.valueOf(new Random().nextInt(100000, 999999));
            verificationCode(otp, email, username);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setFrom("sheengoeoe@gmail.com");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, email);
            mimeMessage.setSubject("Activate Your Account");
            String content = """
                    <!DOCTYPE html>
                                        <html lang="en">
                                        <head>
                                            <meta charset="UTF-8">
                                            <title>Activation Page</title>
                                        </head>
                                        <body>
                                        <div style="background: aqua;">
                                            <h1>Welcome to Instagram, %s</h1>
                                            <h2>Your activation code: </h2>
                                            <h3><b>%s</b></h3>
                                        </div>
                                        </body>
                                        </html>
                    """.formatted(username, otp);
            mimeMessage.setContent(content, "text/html");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verificationCode(String otp, String email, String username) {
        //expiry date from now + 30 minutes
        VerificationCode verificationCode = VerificationCode.childBuilder()
                .code(otp)
                .expiryDate(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
                .username(username)
                .email(email)
                .build();

        verificationRepository.save(verificationCode);
    }

}
