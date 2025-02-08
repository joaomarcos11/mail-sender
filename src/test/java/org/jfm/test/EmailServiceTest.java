package org.jfm.test;

import org.jfm.application.EmailService;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class EmailServiceTest {

    @Inject
    EmailService emailService;

    @Inject
    MockMailbox mockMailbox;

    @BeforeEach
    void setUp() {
        mockMailbox.clear();
    }

    // @Test
    // void testSendEmail() {
    //     String email = "test@example.com";
    //     String messageContent = "Success! Your video is ready!";

    //     emailService.sendEmail(email, messageContent);

    //     assertEquals(1, mockMailbox.getMessagesSentTo(email).size());
    // }

    // @Test
    // public void sendTestMail() {
    //     try {
    //         LOG.debug("Building mail");
    //         Mail mail = Mail.withText("recipient@example.com", "Test Subject", "Test email body.");
    //         LOG.debug("Sending mail via mailer");
    //         mailer.send(mail);
    //         LOG.info("Mail sent successfully.");
    //     } catch (Exception e) {
    //         LOG.error("Error sending mail", e);
    //     }
    // }
}