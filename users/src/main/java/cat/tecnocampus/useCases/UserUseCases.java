package cat.tecnocampus.useCases;

import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.userNotesSources.UserNotesSources;
import cat.tecnocampus.repositories.UserLabRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jordi on 09/02/2017.
 */

@Service
public class UserUseCases {

    private final UserLabRepository userLabRepository;
    private final UserNotesSources messageSource;

    public UserUseCases(UserLabRepository userLabRepository, UserNotesSources messageSource) {
        this.userLabRepository = userLabRepository;
        this.messageSource = messageSource;
    }

    public UserLab createUser(String username, String name, String secondName, String email) {
        UserLab userLab = new UserLab(username, name, secondName, email);
        registerUser(userLab);
        String message = "create user = " + userLab.getUsername();
        messageSource.createUser(message);
        return userLab;
    }

    @Transactional
    public int registerUser(UserLab userLab) {
        try {
            return userLabRepository.save(userLab);
        } catch (DuplicateKeyException e) {
            //throw new UserLabUsernameAlreadyExistsException(userLab.getUsername()); faltaría la exception
            throw e;
        }
    }

    @Transactional
    public void saveUser(UserLab user) {
        userLabRepository.save(user);

        String message = "save user = " + user.getUsername();
        messageSource.saveUser(message);
    }

    public UserLab getUser(String userName) {
        try {
            return userLabRepository.findOne(userName);
        } catch (EmptyResultDataAccessException e) {
            //throw new UserLabNotFoundException("UserLab " + userName + " not found"); // faltarían las excepciones
            throw e;
        }
    }

    public List<UserLab> getUsers() {
        return userLabRepository.findAllLazy();
    }

    public int deleteUser(String username) {
        int res = userLabRepository.delete(username);
        messageSource.deleteUserNotes(username);

        return res;
    }

}
