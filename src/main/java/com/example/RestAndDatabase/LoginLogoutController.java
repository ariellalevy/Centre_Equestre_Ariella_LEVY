package com.example.RestAndDatabase;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

@RestController
public class LoginLogoutController {

    private UserRepository repository;

    public LoginLogoutController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public UserReponse connexion(@RequestBody User userLogin, HttpSession session) {
        UserReponse userReponse = new UserReponse(0,"login", "", null);
        if(session.isNew()){
            session.setMaxInactiveInterval(100);
            for (User user : repository.findAll()) {
                if ((user.getEmail().equals(userLogin.getEmail())) || (user.getPhoneNumber().equals(userLogin.getPhoneNumber()))) {
                    if (BCrypt.checkpw(new String(Base64.getDecoder().decode(userLogin.getPassword())), user.getPassword())) {
                        System.out.println("Valid Credentials");
                        if (!user.isLoggedIn()) {
                            user.setLoggedIn(true);
                            repository.save(user);
                            session.setAttribute("token","randomtoken"+userLogin.getEmail());
                            userReponse.setStatus(200);
                            userReponse.setMessage("User Logged In Successfully!");
                            userReponse.setUserReponse(user);
                            return userReponse;
                        } else {
                            userReponse.setMessage("User Already Logged In");
                            return userReponse;
                        }
                    } else
                        userReponse.setMessage("Password Incorrect Error!!");
                    return userReponse;
                }
            }
            userReponse.setMessage("User Name Not Found");
            return userReponse;
        }
        userReponse.setMessage("User Already Logged In");
        return userReponse;
    }

    @GetMapping("/logout/{id}")
    public String deconnexion(@PathVariable Long id, HttpSession session) {
        if(session.isNew()){
            session.invalidate();
            return "User Not Logged In!!";
        }
        else{
            for(User user: repository.findAll()){
                if(user.getId().equals(id)){
                    if(user.isLoggedIn()) {
                        user.setLoggedIn(false);
                        repository.save(user);
                    }
                    else{
                        return "User Not Logged In";
                    }
                }
            }
            session.invalidate();
            return "User Logged out Successfully";
        }
    }
}
