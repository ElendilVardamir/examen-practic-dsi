package cat.tecnocampus;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Albert on 31/03/2017.
 */
@EnableBinding(MultiSink.MultiInputSink.class)
public class MultiSink {

    @StreamListener(MultiInputSink.INPUT1)
    public synchronized void receive1(String message) {
        System.out.println("******************");
        System.out.println("Delete Notes");
        System.out.println("******************");
        System.out.println("Received message " + message);
    }

    @StreamListener(MultiInputSink.INPUT2)
    public synchronized void receive2(String message) {
        System.out.println("******************");
        System.out.println("UserInfo");
        System.out.println("******************");
        System.out.println("Received message " + message);
    }

    @StreamListener(MultiInputSink.INPUT3)
    public synchronized void receive3(String message) {
        System.out.println("******************");
        System.out.println("NoteInfo");
        System.out.println("******************");
        System.out.println("Received message " + message);
    }

    public interface MultiInputSink {
        String INPUT1 = "input1";

        String INPUT2 = "input2";

        String INPUT3 = "input3";

        @Input(INPUT1)
        SubscribableChannel input1();

        @Input(INPUT2)
        SubscribableChannel input2();

        @Input(INPUT3)
        SubscribableChannel input3();
    }
}
