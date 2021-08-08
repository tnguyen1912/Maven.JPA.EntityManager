package entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cd {

    @Id
    private Integer id;
    private String title;
    private String genre;
    private Integer year;
    private Integer artistId;
    private Integer price;

    public Cd() {
    }

    public Cd(Integer id, String title, String genre, Integer year, Integer artistId, Integer price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.artistId = artistId;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Phone{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", genre=" + genre +
                    ", year=" + year +
                    ", artist_id=" + artistId +
                    ", price=" + price +
                    '}';
        }
    }
}
