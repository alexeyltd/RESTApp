package restful.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restful.model.Employee;
import restful.model.User;
import restful.repository.UserRepository;
import restful.service.UserService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class RestUserController {

    final static Logger logger = Logger.getLogger(RestUserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getEmployee(@PathVariable("id") Long id) {
        User employee = userService.getById(id);

        if (employee == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(employee, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllEmployees() {
        List<User> user = userService.getAll();
        if (user.isEmpty()) {
            logger.debug("User does not exist");
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found " + user.size() + " users");
        logger.debug(user);
        logger.debug(Arrays.toString(user.toArray()));
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> addEmployee(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if (user == null) {
            logger.debug("Employee with id: " + id + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            userService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {

        User user = userService.getById(employee.getId());
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            userService.save(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

}
