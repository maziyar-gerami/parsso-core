package org.apereo.cas.configuration.model.support.sms;

import org.apereo.cas.configuration.support.RequiresModule;
import org.apereo.cas.configuration.model.RestEndpointProperties;
import org.apereo.cas.configuration.model.SpringResourceProperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * This is {@link SmsProvidersProperties}.
 *
 * @author Misagh Moayyed
 * @since 5.3.0
 */
@Getter
@Setter
@RequiresModule(name = "cas-server-core-util", automated = true)
public class SmsProvidersProperties implements Serializable {
    private static final long serialVersionUID = -3713886839517507376L;

    /**
     * Kavenegar settings.
     */
    @NestedConfigurationProperty
    private KavenegarProperties kavenegar = new KavenegarProperties();

    /**
     * Twilio settings.
     */
    @NestedConfigurationProperty
    private TwilioProperties twilio = new TwilioProperties();

    /**
     * TextMagic settings.
     */
    @NestedConfigurationProperty
    private TextMagicProperties textMagic = new TextMagicProperties();

    /**
     * Clickatell settings.
     */
    @NestedConfigurationProperty
    private ClickatellProperties clickatell = new ClickatellProperties();

    /**
     * SNS settings.
     */
    @NestedConfigurationProperty
    private AmazonSnsProperties sns = new AmazonSnsProperties();

    /**
     * Nexmo settings.
     */
    @NestedConfigurationProperty
    private NexmoProperties nexmo = new NexmoProperties();

    /**
     * Groovy script for sending sms notifications.
     */
    private Groovy groovy = new Groovy();

    /**
     * Send SMS via via REST.
     */
    private Rest rest = new Rest();

    @RequiresModule(name = "cas-server-core-util", automated = true)
    @Getter
    @Setter
    public static class Rest extends RestEndpointProperties {
        private static final long serialVersionUID = -8102345678378393382L;
    }

    @RequiresModule(name = "cas-server-core-util", automated = true)
    @Getter
    @Setter
    public static class Groovy extends SpringResourceProperties {
        private static final long serialVersionUID = 8079027843747126083L;
    }
}

