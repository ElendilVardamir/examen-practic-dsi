package cat.tecnocampus.useCases;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domain.NoteLabBuilder;
import cat.tecnocampus.repositories.NoteLabRepository;
import cat.tecnocampus.sourceSink.NotesSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Jordi on 09/02/2017.
 */

@Service
public class NoteUseCases {

    private NoteLabRepository noteLabRepository;

    private final NotesSource messageSource;

    public NoteUseCases(NoteLabRepository noteLabRepository, NotesSource messageSource) {

        this.noteLabRepository = noteLabRepository;
        this.messageSource = messageSource;
    }

    public NoteLab getNote(long id){ return this.noteLabRepository.findOne(id);}


    public List<NoteLab> getUserNotes(String user_id) {

        return noteLabRepository.findAllFromUser(user_id);
    }

    public List<NoteLab> getAllNotes() {
        return noteLabRepository.findAll();
    }

    public NoteLab updateUserNote(NoteLab note, int id) {
        noteLabRepository.updateNote(note, id);

        String message = "update note with ID = " + id;
        messageSource.updateUserNote(message);

        return note;
    }

    public int deleteNote(int id) {
        int res;

        res = noteLabRepository.deleteNote(id);

        String message = "delete note with ID = " + id;
        messageSource.deleteNote(message);

        return res;
    }

    public int deleteUserNotes(String username) {
        int res;

        res = noteLabRepository.deleteUserNotes(username);
        String message = "deleted notes of = " + username;
        System.out.println(message);
        messageSource.deleteUserNotes(message);

        return res;
    }

    public NoteLab addUserNote(NoteLab noteLab) {
        LocalDateTime now = LocalDateTime.now();
        NoteLab note = new NoteLabBuilder().title(noteLab.getTitle()).content(noteLab.getContent()).
                time(now).timeEdit(now).note_id(noteLab.getNote_id()).userId(noteLab.getUserId()).createNoteLab();
        noteLabRepository.save(note);

        String message = "add note = " + noteLab.getContent();
        messageSource.addUserNote(message);

        return note;
    }
}
