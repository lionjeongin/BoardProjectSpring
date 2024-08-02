package org.choongang.email;

import org.choongang.email.service.EmailMessage;
import org.choongang.email.service.EmailSendService;
import org.choongang.email.service.EmailVerifyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class EmailSendTest {
    @Autowired
    private EmailSendService emailSendService;

    @Test
    void sendTest() {
        EmailMessage message = new EmailMessage("cindy93489163@gmail.com", "제목...", "내용...");
        boolean success = emailSendService.sendMail(message);

        assertTrue(success);
    }

    @Test
    @DisplayName("템플릿 형태로 전송 테스트")
    void sendWithTplTest() {
        EmailMessage message = new EmailMessage("cindy93489163@gmail.com", "제목...", "내용...");
        Map<String,Object> tplData = new HashMap<>();
        tplData.put("authNum", "123456");
        boolean success = emailSendService.sendMail(message, "auth", tplData);

        assertTrue(success);
    }

    @Autowired
    private EmailVerifyService emailVerifyService;

    @Test
    @DisplayName("이메일 인증 번호 전송 테스트")
    void emailVerifyTest() {
        boolean result = emailVerifyService.sendCode("cindy93489163@gmail.com");
        assertTrue(result);
    }

}