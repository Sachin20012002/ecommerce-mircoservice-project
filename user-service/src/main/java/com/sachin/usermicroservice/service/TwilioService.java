package com.sachin.usermicroservice.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    private final String ACCOUNT_SID = "AC2e6a542dd7e77c588e6828649f6b79e9";
    private final String AUTH_TOKEN = "b5483380a9feaeb2f232f091734bdf52";
    private final String FROM_NUMBER = "9940491149";

    public void sendOtp(String to, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(FROM_NUMBER),
                "Your OTP is " + otp).create();
    }
}

