package org.jfm.application;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EmailService {

    private static final Logger LOG = Logger.getLogger(EmailService.class);

    @Inject
    Mailer mailer;

    public void sendEmail(String email, String videoId) {
        String body = String.format("Erro no processamento do vídeo (ID: %s) , tente novamente.", videoId);
        try {
            LOG.debug("Attempting to send email to " + email);
            mailer.send(Mail.withText(email, "FiapX Video Notification", body));
            LOG.info("Email sent successfully to " + email);
        } catch (Exception e) {
            LOG.error("Error sending email to " + email, e);
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
