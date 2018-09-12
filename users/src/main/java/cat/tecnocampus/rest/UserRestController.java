package cat.tecnocampus.rest;

import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.useCases.UserUseCases;
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
public class UserRestController {

    private UserUseCases userUseCases;

    @Autowired
    public UserRestController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Resources<UserLab>> listUsers() {
        List <UserLab> listUserLab = userUseCases.getUsers();

        for (UserLab userLab : listUserLab) {
            userLab.add(linkTo(methodOn(UserRestController.class).getUser(userLab.getUsername())).withSelfRel());
        }

        Resources resource = new Resources(listUserLab);
        resource.add(linkTo(methodOn(UserRestController.class).listUsers()).withSelfRel());

        return new ResponseEntity(resource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable String user) {
        UserLab userLab = userUseCases.getUser(user);
        userLab.add(linkTo(methodOn(UserRestController.class).getUser(user)).withSelfRel());

        return new ResponseEntity(userLab, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveUser(@RequestBody UserLab userLab) {
        System.out.println(userLab);
        userUseCases.registerUser(userLab);
        return new ResponseEntity(userLab, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{user}", produces = MediaType.APPLICATION_JSON_VALUE) //necessita json?
    public ResponseEntity deleteUser(@PathVariable ("user") String user) {
        UserLab userLab = userUseCases.getUser(user);
        userUseCases.deleteUser(user);
        return new ResponseEntity(userLab, HttpStatus.OK);
    }
}
