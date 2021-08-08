package services;

import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.ResultSet;
import java.util.List;


/**
 * findById() - done
 * findAll() - done
 * update() - done
 * create() - done
 * delete() - done
 */

public class PersonService implements ServiceInterface {

    public PersonService() {
    }

    public void create(Person person){
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

    public List<Person> findAll(){
        return entityManager.createQuery("SELECT p FROM Person p",Person.class).getResultList();
    }

    public Person findById(Integer id){
        return entityManager.find(Person.class,id);
    }

    public void deleteById(Integer id){
        Person removeMe = findById(id);
        if(removeMe != null){
            entityManager.getTransaction().begin();
            entityManager.remove(removeMe);
            entityManager.getTransaction().commit();
        }
    }

    public void update(Integer id, Person newPerson) {
        entityManager.getTransaction().begin();
        Person updateMe = entityManager.find(Person.class,id);

        updateMe.setFirst(newPerson.getFirst());
        updateMe.setLast(newPerson.getLast());

        entityManager.getTransaction().commit();
    }

}
