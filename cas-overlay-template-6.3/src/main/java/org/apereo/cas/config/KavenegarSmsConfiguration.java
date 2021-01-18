package org.apereo.cas.config;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.support.sms.KavenegarSmsSender;
import org.apereo.cas.util.io.SmsSender;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * This is {@link KavenegarSmsConfiguration}.
 *
 * @author Hamed Monkaresi
 * @since 6.1.4
 */
@Configuration(value = "kavenegarSmsConfiguration", proxyBeanMethods = false)
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class KavenegarSmsConfiguration {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Bean
    @RefreshScope
    public SmsSender smsSender() {
        val kavenegar = casProperties.getSmsProvider().getKavenegar();
        Assert.notNull(kavenegar.getApiKey(), "Kavenegar API Key cannot be blank");
        return new KavenegarSmsSender(kavenegar.getApiKey());
    }
}

