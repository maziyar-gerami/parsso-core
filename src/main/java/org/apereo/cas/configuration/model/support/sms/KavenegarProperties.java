package org.apereo.cas.configuration.model.support.sms;

import org.apereo.cas.configuration.support.RequiredProperty;
import org.apereo.cas.configuration.support.RequiresModule;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * This is {@link KavenegarProperties}.
 *
 * @author Hamed Monkaresi
 * @since 6.1.4
 */
@RequiresModule(name = "cas-server-support-sms-kavenegar")
@Getter
@Setter
public class KavenegarProperties implements Serializable {

    private static final long serialVersionUID = -7043132225482495221L;

    /**
     * Kavenegar account identifier used for authentication.
     */
    @RequiredProperty
    private String apiKey;

}
