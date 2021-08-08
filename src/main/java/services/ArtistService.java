package services;

import entities.Artist;
import java.util.List;

public class ArtistService implements ServiceInterface{

    public ArtistService() {
    }

    public void create(Artist artist){
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
    }

    public List<Artist> findAll(){
        return entityManager.createQuery("SELECT a FROM Artist a",Artist.class).getResultList();
    }

    public Artist findById(Integer id){
        return entityManager.find(Artist.class,id);
    }

    public void deleteById(Integer id){
        Artist removeMe = findById(id);
        if(removeMe != null){
            entityManager.getTransaction().begin();
            entityManager.remove(removeMe);
            entityManager.getTransaction().commit();
        }
    }

    public void update(Integer id, Artist newArtist) {
        entityManager.getTransaction().begin();
        Artist updateMe = entityManager.find(Artist.class,id);
        updateMe.setFirst_name(newArtist.getFirst_name());
        updateMe.setLast_name(newArtist.getLast_name());
        updateMe.setInstrument(newArtist.getInstrument());
        entityManager.getTransaction().commit();
    }
}
