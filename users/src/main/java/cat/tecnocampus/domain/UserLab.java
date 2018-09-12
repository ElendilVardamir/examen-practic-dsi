package cat.tecnocampus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by roure on 19/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLab extends ResourceSupport implements Serializable{

    @Size(min=5, max=15)
    private String username;

    @Size(min=5, max=15)
    private String name;

    @Size(min=5, max=15)
    private String secondName;

    @Email
    private String email;

    private String password;

    public UserLab() {

    }

    //@JsonCreator
    public UserLab(String username, String name, String secondname, String email) {
        this.setUsername(username);
        this.setName(name);
        this.setSecondName(secondname);
        this.setEmail(email);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String toString() {
        return "Usuari: " + this.username + ", " + this.name + " " + this.secondName + " " + this.email + " " + this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLab userLab = (UserLab) o;

        if (username != null ? !username.equals(userLab.username) : userLab.username != null) return false;
        if (name != null ? !name.equals(userLab.name) : userLab.name != null) return false;
        if (secondName != null ? !secondName.equals(userLab.secondName) : userLab.secondName != null) return false;
        if (email != null ? !email.equals(userLab.email) : userLab.email != null) return false;
//        if (noteLabs != null ? !noteLabs.equals(userLab.noteLabs) : userLab.noteLabs != null) return false;
        return password != null ? password.equals(userLab.password) : userLab.password == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (noteLabs != null ? noteLabs.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }


}