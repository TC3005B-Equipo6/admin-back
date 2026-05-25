package com.e6.infrastructure.util;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailService {

    @Inject
    Mailer mailer;

    public void sendPasswordRecoveryEmail(
            String to,
            String resetLink
    ) {

        mailer.send(
            Mail.withText(
                to,
                "Recuperación de contraseña - Move360",

                "Hola,\n\n" +
                "Tu solicitud fue aprobada.\n\n" +
                "Recupera tu contraseña aquí:\n\n"
                + resetLink +
                "\n\nMove360"
            )
        );
    }
}