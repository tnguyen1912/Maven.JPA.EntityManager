package services;


import entities.Cd;
import entities.Person;

import java.util.List;

public class CdService implements ServiceInterface {

    public CdService() {
    }

    public void create(Cd newCd){
        entityManager.getTransaction().begin();
        entityManager.persist(newCd);
        entityManager.getTransaction().commit();
    }

    public List<Cd> findAll(){
        return entityManager.createQuery("SELECT c FROM Cd c",Cd.class).getResultList();
    }

    public Cd findById(Integer id){
        return entityManager.find(Cd.class,id);
    }

    public void deleteById(Integer id){
        Cd removeMe = findById(id);
        if(removeMe != null){
            entityManager.getTransaction().begin();
            entityManager.remove(removeMe);
            entityManager.getTransaction().commit();
        }
    }

    public void update(Integer id, Cd newCd) {
        entityManager.getTransaction().begin();
        Cd updateMe = entityManager.find(Cd.class,id);
        updateMe.setTitle(newCd.getTitle());
        updateMe.setGenre(newCd.getGenre());
        updateMe.setYear(newCd.getYear());
        updateMe.setArtistId(newCd.getArtistId());
        updateMe.setPrice(newCd.getPrice());
        entityManager.getTransaction().commit();
    }
}
