package com.e6.application.usecase;

import com.e6.domain.model.PasswordRecoveryTicket;
import com.e6.domain.repository.PasswordRecoveryTicketRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.UUID;

import com.e6.infrastructure.util.EmailService;

import com.google.firebase.auth.FirebaseAuth;

@ApplicationScoped
public class UpdatePasswordRecoveryTicketStatusUseCase {

    @Inject
    PasswordRecoveryTicketRepository passwordRecoveryTicketRepository;

    @Inject
    EmailService emailService;

    public PasswordRecoveryTicket execute(
        UUID id,
        String status
) throws Exception {

    PasswordRecoveryTicket ticket =
            passwordRecoveryTicketRepository.findById(id);

    if(ticket==null){
        return null;
    }

    if(status.equals("APPROVED")){

        String resetLink =
                FirebaseAuth.getInstance()
                .generatePasswordResetLink(
                        ticket.getEmail()
                );

        emailService.sendPasswordRecoveryEmail(
                ticket.getEmail(),
                resetLink
        );

        status="SENT";
    }

    ticket.setStatus(status);
    ticket.setResolvedAt(LocalDateTime.now());

    return passwordRecoveryTicketRepository.update(ticket);
}
}