package jdbcTools;

import entities.Artist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository implements Repo {

    private Connection connection;

    public ArtistRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    public void createArtist(Artist artist){
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO walmart.artist")
                        .append("(id, first_name, last_name, instrument)")
                        .append("VALUES (%s, %s, %s, %s);")
                        .toString(),
                artist.getId(),
                artist.getFirst_name(),
                artist.getLast_name(),
                artist.getInstrument()
        ));
    }

    public List<Artist> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM walmart.artist;");
        List<Artist> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String instrument = resultSet.getString(4);
                list.add(new Artist(
                        id,
                        firstName,
                        lastName,
                        instrument));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Artist read(Integer artistId) {
        return readAll()
                .stream()
                .filter(artist -> artist.getId() == artistId)
                .findAny()
                .get();
    }

    public void update(Integer id, Artist newArtist) {
        executeStatement(new StringBuilder()
                .append("UPDATE artist SET ")
                .append("id = " + newArtist.getId().toString())
                .append(", first_name = " + newArtist.getFirst_name())
                .append(", last_name = " + newArtist.getInstrument())
                .append(", instrument = " + newArtist.getInstrument())
                .append("WHERE id =" + id.toString())
                .toString());
    }

    public void delete(Integer id) {
        executeStatement("DELETE FROM artist WHERE id = " + id.toString());
    }

    public void delete(Artist artist) {
        executeStatement("DELETE FROM artist WHERE id = " + artist.getId().toString());
    }

}
