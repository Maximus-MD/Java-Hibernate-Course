package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.*;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;


public class Lesson4_Relation {
    public static void main(String[] args) {
        //using xml configuration
        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

        //using java class configuration
        String persistenceUnitName = "pu-name";

        Map<String , String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName), props);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            Person person = new Person();
            person.setName("Obama");

            Passport passport = new Passport();
            passport.setNumber("AB014555");

            person.setPassport(passport);
            passport.setPerson(person);

            entityManager.persist(person);
            entityManager.persist(passport);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

    }
}