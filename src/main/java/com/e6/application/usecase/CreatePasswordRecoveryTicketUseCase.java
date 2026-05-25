package com.e6.application.usecase;

import com.e6.application.dto.PasswordRecoveryRequestDto;
import com.e6.domain.model.PasswordRecoveryTicket;
import com.e6.domain.repository.PasswordRecoveryTicketRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class CreatePasswordRecoveryTicketUseCase {

    @Inject
    PasswordRecoveryTicketRepository passwordRecoveryTicketRepository;

    public PasswordRecoveryTicket execute(PasswordRecoveryRequestDto request) {
        PasswordRecoveryTicket ticket = new PasswordRecoveryTicket();

        ticket.setId(UUID.randomUUID());
        ticket.setEmail(request.email);
        ticket.setStatus("PENDING");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setResolvedAt(null);

        return passwordRecoveryTicketRepository.create(ticket);
    }
}