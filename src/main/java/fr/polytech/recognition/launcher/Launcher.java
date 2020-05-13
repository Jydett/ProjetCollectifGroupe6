package fr.polytech.recognition.launcher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Launcher {
    public static void main(String[] args) {
        //Initialisation de l'entityManager
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("test");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }
}
