package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface ServiceInterface {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu2");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
}
