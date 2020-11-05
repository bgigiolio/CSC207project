public class MessageCreator {
    protected Attendee reciever;
    protected Message message;

    public MessageCreator(String toBeMessage, Attendee reciever, String senderUserid){
        this.reciever = reciever;
        message = new Message(toBeMessage, senderUserid);
        deliver();
    }

    private void deliver(){
        new MessageController(this.reciever, this.message);
    }


}
