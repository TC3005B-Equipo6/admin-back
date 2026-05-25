package com.e6.infrastructure.repository;

import com.e6.domain.model.PasswordRecoveryTicket;
import com.e6.domain.repository.PasswordRecoveryTicketRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PasswordRecoveryTicketRepositoryImpl implements PasswordRecoveryTicketRepository {

    private final List<PasswordRecoveryTicket> tickets = new ArrayList<>();

    @Override
    public PasswordRecoveryTicket create(PasswordRecoveryTicket ticket) {
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public List<PasswordRecoveryTicket> findByStatus(String status) {
        return tickets.stream()
                .filter(ticket -> ticket.getStatus().equals(status))
                .toList();
    }

    @Override
    public PasswordRecoveryTicket findById(UUID id) {
        return tickets.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public PasswordRecoveryTicket update(PasswordRecoveryTicket ticket) {
        return ticket;
    }
}