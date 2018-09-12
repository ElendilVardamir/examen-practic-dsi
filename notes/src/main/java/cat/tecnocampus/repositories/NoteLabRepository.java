package cat.tecnocampus.repositories;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domain.NoteLabBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by roure on 20/09/2016.
 */
@Repository
public class NoteLabRepository {
	
    private final JdbcTemplate jdbcTemplate;

    public NoteLabRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NoteLab> findAll() {
        return jdbcTemplate.query("Select * from note_lab", new NoteLabMapper());
    }

    public NoteLab findOne(long id) {
        return jdbcTemplate.queryForObject("Select * from note_lab where id = ?", new NoteLabMapper(), id);
    }

    public int save(NoteLab noteLab) {
        return jdbcTemplate.update("insert into note_lab (title, content, date_creation, date_edit, user_id) values(?, ?, ?, ?, ?)",
                noteLab.getTitle(), noteLab.getContent(), Timestamp.valueOf(noteLab.getDateCreation()), Timestamp.valueOf(noteLab.getDateEdit()), noteLab.getUserId());
    }

    public int updateNote(NoteLab note, int id) {
        System.out.println(note);
        return jdbcTemplate.update("update note_lab set title = ?, content = ?, date_edit = ? where id = ?",
                note.getTitle(), note.getContent(), Timestamp.valueOf(LocalDateTime.now()), id);
    }

    public int deleteNote(int id) {
        return jdbcTemplate.update("delete from note_lab where id = ?", id);
    }

    public boolean existsNoteTitle(String title) {
        int countOfNotes = jdbcTemplate.queryForObject(
                "select count(*) from note_lab where title = ?", Integer.class, title);
        return countOfNotes > 0;
    }

    public List<NoteLab> findAllFromUser(String user_id) {
        return jdbcTemplate.query("select * from note_lab where user_id = ?", new NoteLabMapper(), user_id);
    }

    public int deleteUserNotes(String username) {
        return jdbcTemplate.update("delete from note_lab where user_id = ?", username);
    }

    private final class NoteLabMapper implements RowMapper<NoteLab> {
        @Override
        public NoteLab mapRow(ResultSet resultSet, int i) throws SQLException {
            return new NoteLabBuilder()
                    .note_id(resultSet.getInt("id"))
            		.title(resultSet.getString("title"))
                    .content(resultSet.getString("content"))
                    .time(resultSet.getTimestamp("date_creation").toLocalDateTime())
                    .timeEdit(resultSet.getTimestamp("date_edit").toLocalDateTime())
                    .userId(resultSet.getString("user_id"))
                    .createNoteLab();
        }
    }

}
