package com.mikusher.apieurom.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.mikusher.apieurom.config.TwilioConfiguration;
import com.mikusher.apieurom.repository.SmsSender;
import com.mikusher.apieurom.request.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    private static final Logger Log = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())){
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsRequest.getMessage();

            MessageCreator mCreator = Message.creator(to, from, message);
            mCreator.create();
            Log.info("Send SMS {}", smsRequest);

        }else {
            throw new IllegalArgumentException("Phone number not valid "+ smsRequest.getPhoneNumber());
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // validate
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber swissNumberProto = new Phonenumber.PhoneNumber();
        try {
            swissNumberProto = phoneUtil.parse(phoneNumber, "PT");
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }
        return phoneUtil.isValidNumber(swissNumberProto);
    }
}
