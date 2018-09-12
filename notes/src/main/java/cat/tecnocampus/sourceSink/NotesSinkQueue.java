package cat.tecnocampus.sourceSink;

import cat.tecnocampus.useCases.NoteUseCases;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by josep on 2/3/17.
 */
@EnableBinding(Sink.class)
public class NotesSinkQueue {

    private NoteUseCases noteUseCases;
    private Source source;

    @Autowired
    public void setNoteUseCases(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }


    @StreamListener(Sink.INPUT)
    public void deleteNotesSink(String message) {
        noteUseCases.deleteUserNotes(message);
    }
}


