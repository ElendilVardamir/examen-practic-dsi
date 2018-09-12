package cat.tecnocampus.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by roure on 19/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteLab extends ResourceSupport implements Serializable{

    private int note_id;

    private String title;

    private String content;

    @JsonFormat(pattern = "dd:MM:yyyy HH:mm:ss")
    private final LocalDateTime dateCreation;

    @JsonFormat(pattern = "dd:MM:yyyy HH:mm:ss")
    private LocalDateTime dateEdit;

    private String userId;

    public NoteLab(int note_id, String title, String content, LocalDateTime time, LocalDateTime timeEdit, String userId) {
        this.note_id = note_id;
        this.setTitle(title);
        this.setContent(content);
        this.dateCreation = time;
        this.userId = userId;
        this.setDateEdit(timeEdit);
    }

    public NoteLab() {
        dateCreation = LocalDateTime.now();
        dateEdit=dateCreation;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(LocalDateTime date_edit) {
        this.dateEdit = date_edit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toString(){
        return "NoteLab: "+this.title+", Content: "+ this.content;
    }
}