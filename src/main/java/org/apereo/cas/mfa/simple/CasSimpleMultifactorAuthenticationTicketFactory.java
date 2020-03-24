package org.apereo.cas.mfa.simple;

import org.apereo.cas.authentication.principal.Service;
import org.apereo.cas.ticket.ExpirationPolicyBuilder;
import org.apereo.cas.ticket.Ticket;
import org.apereo.cas.ticket.TicketFactory;
import org.apereo.cas.ticket.TransientSessionTicket;
import org.apereo.cas.ticket.TransientSessionTicketFactory;
import org.apereo.cas.ticket.TransientSessionTicketImpl;
import org.apereo.cas.ticket.UniqueTicketIdGenerator;

import lombok.RequiredArgsConstructor;
import lombok.val;

import java.io.Serializable;
import java.util.Map;

/**
 * This is {@link CasSimpleMultifactorAuthenticationTicketFactory}.
 *
 * @author Misagh Moayyed
 * @since 6.0.0
 */
@RequiredArgsConstructor
public class CasSimpleMultifactorAuthenticationTicketFactory implements TransientSessionTicketFactory {
    /**
     * MFA ticket prefix.
     */
    public static final String PREFIX = "P";

    private final ExpirationPolicyBuilder expirationPolicy;
    private final UniqueTicketIdGenerator ticketIdGenerator;

    /**
     * Create delegated authentication request ticket.
     *
     * @param service    the service
     * @param properties the properties
     * @return the delegated authentication request ticket
     */
    @Override
    public TransientSessionTicket create(final Service service, final Map<String, Serializable> properties) {
        val id = ticketIdGenerator.getNewTicketId(PREFIX);
        return new TransientSessionTicketImpl(id, expirationPolicy.buildTicketExpirationPolicy(), service, properties);
    }

    @Override
    public TransientSessionTicket create(final String id, final Map<String, Serializable> properties) {
        return new TransientSessionTicketImpl(TransientSessionTicketFactory.normalizeTicketId(id),
            expirationPolicy.buildTicketExpirationPolicy(), null, properties);
    }

    @Override
    public TicketFactory get(final Class<? extends Ticket> clazz) {
        return this;
    }
}

