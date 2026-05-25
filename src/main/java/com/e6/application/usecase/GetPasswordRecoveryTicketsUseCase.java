package com.e6.application.usecase;

import com.e6.domain.model.PasswordRecoveryTicket;
import com.e6.domain.repository.PasswordRecoveryTicketRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetPasswordRecoveryTicketsUseCase {

    @Inject
    PasswordRecoveryTicketRepository passwordRecoveryTicketRepository;

    public List<PasswordRecoveryTicket> execute(String status) {
        return passwordRecoveryTicketRepository.findByStatus(status);
    }
}