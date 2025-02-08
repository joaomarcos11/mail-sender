package org.jfm.test;

import org.jfm.application.EmailService;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class EmailServiceTest {

    @Mock
    private Mailer mailer;

    @InjectMocks
    private EmailService emailService;

    private final String email = "test@example.com";
    private final String videoId = "12345";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // BDD
    // Feature: Envio de email com sucesso
    // Cenário: Envio de email com sucesso
    @Test
    void sendEmailSucesso() {
       // Dado email = "test@example.com" válido
       // E dado String videoId = "e1ea5f84-be64-4589-a6bc-5894fd0018db" 
       // Quando chamar método sendEmail com endereço de email e ID de video válidoas
       emailService.sendEmail(email, videoId);
       
        // Então, o método deve ser executado com sucesso
        verify(mailer, times(1)).send(any(Mail.class));
    }

    @Test
    void sendEmailFalha() {
        doThrow(new RuntimeException("Mail server error")).when(mailer).send(any(Mail.class));

        RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class, 
                () -> emailService.sendEmail(email, videoId)
        );

        verify(mailer, times(1)).send(any(Mail.class));
    }
}
