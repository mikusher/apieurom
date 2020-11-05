package com.mikusher.apieurom.repository;

import com.mikusher.apieurom.request.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);
}
