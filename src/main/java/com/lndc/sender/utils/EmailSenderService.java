package com.lndc.sender.utils;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String message);
}
