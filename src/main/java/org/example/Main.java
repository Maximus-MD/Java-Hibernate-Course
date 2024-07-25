package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Employee;
import org.example.entities.Product;
import org.example.entities.Student;
import org.example.entities.keys.StudentKey;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        //using xml configuration
        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

        //using java class configuration
        String persistenceUnitName = "pu-name";

        Map<String , String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none");

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName), props);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

//            Product p1 = new Product();
//            p1.setCode("qwe");
//            p1.setNumber(2);
//            p1.setColor("green");
//            entityManager.persist(p1);
            //String getId = e1.getId();
//            System.out.println("id: " + getId + " " + e1);

            StudentKey id = new StudentKey();
            id.setCode("qwe");
            id.setNumber(32);

            Student s = new Student();
            s.setId(id);
            s.setName("Lincoln");

            entityManager.persist(s);

            Student findedStudent = entityManager.find(Student.class, id);
            System.out.println(findedStudent);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

    }
}