public class MessageCreator {
    protected Attendee sender;
    protected Attendee reciever;
    protected Message message;

    public MessageCreator(String toBeMessage, Attendee reciever, Attendee sender){
        this.reciever = reciever;
        this.sender = sender;
        message = new Message(toBeMessage, sender.getUserid());
        deliver();
    }

    private void deliver(){
        new MessageController(this.reciever, this.message);
    }


}
