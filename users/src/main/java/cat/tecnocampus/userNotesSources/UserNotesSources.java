package cat.tecnocampus.userNotesSources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by josep on 2/3/17.
 */
@EnableBinding(Source.class)
public class UserNotesSources {

    private Source notesChannel;

    @Autowired
    public void setNotesChannel(Source notesChannel) {
        this.notesChannel = notesChannel;
    }

    public void deleteUserNotes(String username) {
        notesChannel.output().send(MessageBuilder.withPayload(username).build());
    }

    public void saveUser(String message) {
        notesChannel.output().send(MessageBuilder.withPayload(message).build());
    }

    public void createUser(String message) {
        notesChannel.output().send(MessageBuilder.withPayload(message).build());
    }
}

