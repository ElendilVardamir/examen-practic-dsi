package cat.tecnocampus.domain;

import java.time.LocalDateTime;

public class NoteLabBuilder {
    private int note_id;
    private String title;
    private String content;
    private LocalDateTime time;
    private LocalDateTime timeEdit;
    private String userId;

    public NoteLabBuilder note_id(int note_id) {
        this.note_id = note_id;
        return this;
    }
    public NoteLabBuilder title(String title) {
        this.title = title;
        return this;
    }

    public NoteLabBuilder content(String content) {
        this.content = content;
        return this;
    }

    public NoteLabBuilder time(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public NoteLabBuilder timeEdit(LocalDateTime timeEdit) {
        this.timeEdit = timeEdit;
        return this;
    }

    public NoteLabBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }

    public NoteLab createNoteLab() {
        return new NoteLab(note_id,title,content,time,timeEdit,userId);
    }
}