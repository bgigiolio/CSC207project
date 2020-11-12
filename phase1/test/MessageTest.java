import static org.junit.Assert.*;

public class MessageTest {

    public void testMessage(){
        Message m = new Message("hello", "a");

    }
    public void testMessageGetters(){
        Message m = new Message("hello", "a");
        assertEquals("incorrect getContent", "hello", m.getContent());
        assertEquals("incorrect getSender", "a", m.getSender());
        assertNotNull("incorrect getTime_sent", m.getTime_sent());

    }
}
