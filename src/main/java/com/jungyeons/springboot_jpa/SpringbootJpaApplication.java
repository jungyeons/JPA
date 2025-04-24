package com.jungyeons.springboot_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    EntityManagerFactory emf;
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpringbootJpaApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        //엔티티 매니저는 사용자당 하나
        EntityManager em = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();
        System.out.println("em="+em);
        System.out.println("em2="+em2);

        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");

    }
}
