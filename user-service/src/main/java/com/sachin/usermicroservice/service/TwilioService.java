package com.sachin.usermicroservice.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    @Value("${twilio.ACCOUNT_SID}")
    private  String ACCOUNT_SID;

    @Value("${twilio.AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Value("${twilio.FROM_NUMBER}")
    private String FROM_NUMBER;

    public void sendOtp(String to, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(FROM_NUMBER),
                "Your OTP is " + otp).create();
        System.out.println(message);
    }
}

