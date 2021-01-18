package org.apereo.cas.support.sms;

import org.apereo.cas.notifications.sms.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.ArrayList;

import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.excepctions.*;
import com.kavenegar.sdk.models.*;

/**
 * This is {@link KavenegarSmsSender}.
 *
 * @author Hamed Monkaresi
 * @since 6.1.4
 */
@Slf4j
public class KavenegarSmsSender implements SmsSender {
	Logger LOGGER = LoggerFactory.getLogger(KavenegarSmsSender.class);
	private static KavenegarApi api;
    public KavenegarSmsSender(final String apiKey) {
        api = new KavenegarApi(apiKey);
    }
    @Override
    public boolean send(final String from, final String to, final String message) {
        try {
            String sender = from;
            String receptor = to;
            String myMessage = message;
            //SendResult Result = api.send(sender, receptor, myMessage);
            SendResult Result = api.verifyLookup(receptor, message, "", "", "mfa");
            return true;
        } catch (HttpException e) {
			LOGGER.error(e.getMessage(), e);
        } catch (ApiException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }
}

