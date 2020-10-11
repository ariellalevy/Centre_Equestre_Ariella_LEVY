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
    public void newUser(@RequestBody User newUser, HttpServletResponse response) throws IOException {
        repository.save(newUser);
        String message = "Félicitation vous êtes inscris a l'adresse mail suivante: " + newUser.getEmail() + " votre mot de passe par default est: " + newUser.getPassword() + " pour une meilleur sécurité nous vous conseillons de le changer";
        SendEmail.sendingEmail(newUser.getEmail(), "Inscription", message);
        response.getWriter().println(newUser.getId());
    }

    @PutMapping("/user/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    if(newUser.getFirstName()!=null){
                        user.setFirstName(newUser.getFirstName());
                    }if(newUser.getLastName()!=null){
                        user.setLastName(newUser.getLastName());
                    }if(newUser.getEmail()!=null){
                        user.setEmail(newUser.getEmail());
                    }if(newUser.getPassword()!=null){
                        user.setPassword(newUser.getPassword());
                    }if(newUser.getPhoneNumber()!=null){
                        user.setPhoneNumber(newUser.getPhoneNumber());
                    }if(newUser.getLicenceNumber()!=null){
                        user.setLicenceNumber(newUser.getLicenceNumber());
                    }if(newUser.getRole()!=null){
                        user.setRole(newUser.getRole());
                    }
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
