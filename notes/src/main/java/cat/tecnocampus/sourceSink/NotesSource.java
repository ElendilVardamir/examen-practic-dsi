package cat.tecnocampus.sourceSink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by Albert on 31/03/2017.
 */
@EnableBinding(Source.class)
public class NotesSource {

    private Source notesChannel;

    @Autowired
    public void setNotesChannel(Source notesChannel) {
        this.notesChannel = notesChannel;
    }

    public void addUserNote(String message) {
        notesChannel.output().send(MessageBuilder.withPayload(message).build());
    }

    public void deleteUserNotes(String message) {
        notesChannel.output().send(MessageBuilder.withPayload(message).build());
    }

    public void deleteNote(String message) {
        notesChannel.output().send(MessageBuilder.withPayload(message).build());
    }

    public void updateUserNote(String message) {
        notesChannel.output().send(MessageBuilder.withPayload(message).build());
    }
}
