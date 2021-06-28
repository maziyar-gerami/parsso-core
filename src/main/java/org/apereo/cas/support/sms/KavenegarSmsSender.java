package org.apereo.cas.support.sms;
import org.apereo.cas.support.sms.MagfaSDK.*;
import org.apereo.cas.notifications.sms.SmsSender;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.ArrayList;

//import com.kavenegar.sdk.KavenegarApi;
//import com.kavenegar.sdk.excepctions.*;
//import com.kavenegar.sdk.models.*;

/**
 * This is {@link KavenegarSmsSender}.
 *
 * @author Hamed Monkaresi
 * @since 6.1.4
 */
@Slf4j
public class KavenegarSmsSender implements SmsSender {
        Logger LOGGER = LoggerFactory.getLogger(KavenegarSmsSender.class);
        //private static KavenegarApi api;
        private static String username;
        private static String password;
        private static String domain;

    public KavenegarSmsSender(final String apiKey) {
        //api = new KavenegarApi("366A333130424C305555495435436F4C6A57395032413D3D");
        //api = new KavenegarApi(apiKey);

        //----------- NEW
        username = "falat_2394";
        password = "JAEReSP5m9UAF0YM";
        domain = "magfa";
    }
    @Override
    public boolean send(final String from, final String to, final String message) {
        try {
            //String sender = from;
			String sender = "300002394";
            String receptor = to;
 			String myMessage = "سلام. کد احراز هویت پارسو:";
			myMessage = myMessage.concat(message);

            // -------- NEW
            URL url = new URL("https://sms.magfa.com/api/soap/sms/v2/server?wsdl");
            MagfaSoapServer service = new MagfaSoapServer_Service(url).getMagfaSoapServer();
            BindingProvider prov = (BindingProvider) service;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username + "/" + domain);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            HashMap httpHeaders = new HashMap<>();
            httpHeaders.put("Content-Encoding", Collections.singletonList("gzip"));//this indicates you're sending a compressed request
			httpHeaders.put("Accept-Encoding", Collections.singletonList("gzip")); //this says you're willing to accept a compressed response
			prov.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
			StringArray messages = new StringArray();
			List messageItems = messages.getItem();
			messageItems.add(myMessage);
			StringArray recipients = new StringArray();
			List recipientItems = recipients.getItem();
			recipientItems.add(receptor);
			StringArray senders = new StringArray();
			List senderItems = senders.getItem();
			senderItems.add(sender);
			LongArray uids = new LongArray();
			List uidItems = uids.getItem();
			uidItems.add(1L);
			SendResult result = service.send(messages, senders, recipients, uids, new IntArray(), new StringArray(), new IntArray());
			ArrayList<String> res= new ArrayList<>();
			res.add(String.valueOf(result.getStatus()));
			for (SendMessage sendMessage : result.getMessages()) {
					res.add(sendMessage.toString());
			}

            return true;
        } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
        }
        return false;
    }
}

