package com.example.RestAndDatabase;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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

    @GetMapping("/usersMoniteurCavalier")
    public List<User> listMoniteurCavalier(){
        List<User> users = new ArrayList<User>();
        users.addAll(repository.findByRole("moniteur"));
        users.addAll(repository.findByRole("cavalier"));
        return users;
    }

    @GetMapping("/usersAdmin")
    public List<User> listAdmin(){
        return repository.findByRole("admin");
    }

    @PostMapping("/user")
    public void newUser(@RequestBody User newUser, HttpServletResponse response) throws IOException {
        newUser.setPassword(Utility.hashPassword(newUser.getPassword()));
        repository.save(newUser);
        String message = "Félicitation vous êtes inscris a l'adresse mail suivante: " + newUser.getEmail() + " Si vous n'êtes pas le créateur du compte veuilliez cliquer sur ce lien: http://localhost:4200/password pour une changer le mot de passe.";
        Utility.sendingEmail(newUser.getEmail(), "Inscription", message);
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
                        user.setPassword(Utility.hashPassword(newUser.getPassword()));
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
    public void deleteUser(@PathVariable Long id, HttpServletResponse response) throws IOException {
        repository.deleteById(id);
        JSONObject jo = new JSONObject();
        jo.put("type", "deleteUser");
        jo.put("status", response.getStatus());
        response.getWriter().println(jo.toString());
    }

    @GetMapping ("/connexionbyemail")
    public User findPassword(@RequestParam(value="email") String email) {
        return repository.findByEmail(email);
    }

    @GetMapping ("/getNewPassword")
    public void createPassword(@RequestParam(value="email") String email, HttpServletResponse response) throws IOException{
        System.out.println(repository.findByEmail(email));
        User userPassword = repository.findByEmail(email);
        String message = "Vous avez fait une demande de mot de passe pour l'adresse mail suivante: " + userPassword.getEmail() + " cliquer sur ce lien : https://localhost:4200/password pour une changer le mot de passe";
        Utility.sendingEmail(userPassword.getEmail(), "Demande de mot de passe", message);
        response.getWriter().println(response.getStatus());
    }

    @GetMapping("/modifyMdp")
    public User modifyMdp(@RequestParam(value="email") String email,@RequestParam(value="password") String password, @RequestParam(value="passwordConfirm") String passwordConfirm) {
        if(password.equals(passwordConfirm)){
            User userPassword = repository.findByEmail(email);
            return repository.findById(userPassword.getId())
                    .map(user -> {
                        if(userPassword.getPassword()!=null){
                            System.out.println(password);
                            user.setPassword(Utility.hashPassword(password));
                        }
                        return repository.save(user);
                    })
                    .orElseGet(() -> {
                        userPassword.setId(id);
                        return repository.save(userPassword);
                    });
        }
        return null;
    }
}
