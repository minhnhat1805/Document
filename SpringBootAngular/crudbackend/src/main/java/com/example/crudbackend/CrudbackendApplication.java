 package com.example.crudbackend;

import com.example.crudbackend.entity.User;
import com.example.crudbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.example.crudbackend.repository")
@EnableJpaAuditing()
@SpringBootApplication
public class CrudbackendApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CrudbackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
