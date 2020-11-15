package Entities;

import java.time.*;

public class Message {
    private final String content;
    private final String sender;
    private final LocalDateTime time_sent;

    public Message(String content, String sender){
        this.content = content;
        this.sender = sender;
        time_sent = LocalDateTime.now(ZoneId.of("America/Toronto"));
    }

    public String getContent(){
        return content;
    }

    public String getSender(){
        return sender;
    }

    public LocalDateTime getTime_sent() {
        return time_sent;
    }
}
