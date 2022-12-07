package sn.isi.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.User;
import sn.isi.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userServiceImpl;
    @GetMapping
    public List<User> getUsers() {
        return userServiceImpl.getUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") int id) {
        return userServiceImpl.getUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") int id, @Valid @RequestBody User user) {
        return userServiceImpl.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userServiceImpl.deleteUser(id);
    }
}
