public class MessageCreator {
    protected Attendee receiver;
    protected Message message;

    public MessageCreator(String toBeMessage, Attendee receiver, String senderUserid){
        this.receiver = receiver;
        message = new Message(toBeMessage, senderUserid);
        deliver();
    }

    private void deliver(){
        new MessageController(this.receiver, this.message);
    }

}
