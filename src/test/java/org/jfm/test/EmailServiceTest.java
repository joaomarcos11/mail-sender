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

    @Test
    void sendEmail_success() {
        // Act
        emailService.sendEmail(email, videoId);

        // Assert
        verify(mailer, times(1)).send(any(Mail.class));
    }

    @Test
    void sendEmail_failure() {
        // Arrange
        doThrow(new RuntimeException("Mail server error")).when(mailer).send(any(Mail.class));

        // Act & Assert
        RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class, 
                () -> emailService.sendEmail(email, videoId)
        );

        verify(mailer, times(1)).send(any(Mail.class));
    }
}