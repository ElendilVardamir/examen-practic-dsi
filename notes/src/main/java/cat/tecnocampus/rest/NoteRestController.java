package cat.tecnocampus.rest;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.useCases.NoteUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Jordi on 09/02/2017.
 */

@RequestMapping("/api")
@RestController
public class NoteRestController {

    private NoteUseCases noteUseCases;

    @Autowired
    public NoteRestController(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allNotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<NoteLab> getAllNotes() {

        List<NoteLab> listUserLabNotes = noteUseCases.getAllNotes();

        for (NoteLab noteLab : listUserLabNotes){
            noteLab.add(linkTo(methodOn(NoteRestController.class).getNote(noteLab.getNote_id())).withSelfRel());
        }

        Resources<NoteLab> resource = new Resources <NoteLab>(listUserLabNotes);
        resource.add(linkTo(methodOn(NoteRestController.class).getAllNotes()).withSelfRel());

        return resource;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{note_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteLab> getNote(@PathVariable("note_id") int note_id) {

        NoteLab noteLab = noteUseCases.getNote(note_id);

        noteLab.add(linkTo(methodOn(NoteRestController.class).getNote(note_id)).withSelfRel());

        return new ResponseEntity(noteLab, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<NoteLab> getUserNotes(@PathVariable("user_id") String user_id) {

        List<NoteLab> listUserLabNotes = noteUseCases.getUserNotes(user_id);

        for (NoteLab noteLab : listUserLabNotes){
            noteLab.add(linkTo(methodOn(NoteRestController.class).getNote(noteLab.getNote_id())).withSelfRel());
        }

        Resources<NoteLab> resource = new Resources <NoteLab>(listUserLabNotes);
        resource.add(linkTo(methodOn(NoteRestController.class).getUserNotes(user_id)).withSelfRel());

        return resource;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createNote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNote(@RequestBody NoteLab noteLab) {
        noteUseCases.addUserNote(noteLab);
        return new ResponseEntity(noteLab, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{note_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateNote(@RequestBody NoteLab noteLab, @PathVariable("note_id") int note_id) {
        noteUseCases.updateUserNote(noteLab,note_id);
        return new ResponseEntity(noteLab, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{note_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteNote(@PathVariable("note_id") int note_id) {
        NoteLab noteLab =  noteUseCases.getNote(note_id);
        noteUseCases.deleteNote(note_id);

        return new ResponseEntity(noteLab, HttpStatus.OK);
    }


}
