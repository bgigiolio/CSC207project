package main.java.Entities;

import Entities.Message;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class AccessibilityOptions extends Message {

    private final LocalDateTime time_sent;


    public AccessibilityOptions(String request, String sender){
        super(request, sender);
        time_sent = LocalDateTime.now(ZoneId.of("America/Toronto"));
    }
}
