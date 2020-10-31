package com.example.RestAndDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new User("Administateur", "Admin","admin@gmail.com","password", "0130645539", "", "admin"));
        };
    }

    @Bean
    public CommandLineRunner demoCheval(ChevalRepository repository) {
        return (args) -> {
            // save a few cheval
            repository.save(new Cheval("Kafir","poney",200.00, 1.50));
        };
    }

    @Bean
    public CommandLineRunner demoCours(CoursRepository repository) {
        return (args) -> {
            // save a few cours
            Cours cours = new Cours("Cour debutant", "2020-10-20", "13h-14h30", 10, 1, "Laurent Dupont");
            repository.save(cours);
        };
    }

    @Bean
    public CommandLineRunner demoCour(CavalierChevalRepository repository) {
        return (args) -> {
            // save a few cours
            repository.save(new CavalierCheval(3, "Emma Bertier", "Kafir"));
        };
    }
}