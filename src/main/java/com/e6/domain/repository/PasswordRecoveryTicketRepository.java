package com.e6.domain.repository;

import com.e6.domain.model.PasswordRecoveryTicket;

import java.util.List;
import java.util.UUID;

public interface PasswordRecoveryTicketRepository {
    PasswordRecoveryTicket create(PasswordRecoveryTicket ticket);
    List<PasswordRecoveryTicket> findByStatus(String status);
    PasswordRecoveryTicket findById(UUID id);
    PasswordRecoveryTicket update(PasswordRecoveryTicket ticket);
}