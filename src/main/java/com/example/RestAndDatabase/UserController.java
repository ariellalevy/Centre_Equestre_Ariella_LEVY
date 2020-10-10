package com.example.RestAndDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
public class UserController {

    private UserRepository repository;
    Long id = Long.valueOf(1);

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public Iterable<User> Users(){
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> User(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PutMapping("/user/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    user.setLicenceNumber(newUser.getLicenceNumber());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping ("/connexionbyemail")
    public User findPassword(@RequestParam(value="email") String email) {
        return repository.findByEmail(email);
    }

    @GetMapping("/age")
    void manual(HttpServletResponse response) throws IOException {
        response.setHeader("Custom-Header", "foo");
        response.setStatus(200);
        response.getWriter().println(response.getStatus());
    }
}
