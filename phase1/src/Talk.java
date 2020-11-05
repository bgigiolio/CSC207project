public class Talk extends Event{
    protected String speaker;
    public Talk(String title, String location, String datetime, String speaker_name) {
        super(title, location, datetime);
        this.speaker = speaker_name;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
