package com.renovaus.controller;
import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;


/**
 * Created by Olga on 8/22/2016.
 */
public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text,
                           String from);
    
    void sendSimpleMessage(String to,
            String subject,
            String text);
   
}
