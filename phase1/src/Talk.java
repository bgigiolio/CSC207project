import java.util.Calendar;

public class Talk extends Event{
    protected String speaker;
    public Talk(String title, String location, Calendar datetime) {
        super(title, location, datetime);
        this.speaker = "None";
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
