package jdbcTools;

import entities.Cd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CdRepository implements Repo {

    private Connection connection;

    public CdRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    public void createCd(Cd cd){
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO walmart.cd")
                        .append("(id, title, genre, year, artist_id, price)")
                        .append("VALUES (%s, %s, %s, %s, %s, %s);")
                        .toString(),
                cd.getId(),
                cd.getTitle(),
                cd.getGenre(),
                cd.getYear(),
                cd.getArtistId(),
                cd.getPrice()));
    }

    public List<Cd> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM walmart.cd;");
        List<Cd> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String genre = resultSet.getString(3);
                String year = resultSet.getString(4);
                Integer artistId = resultSet.getInt(5);
                String price = resultSet.getString(6);
                list.add(new Cd(
                        id,
                        title,
                        genre,
                        Integer.parseInt(year),
                        artistId,
                        Integer.parseInt(price)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Cd read(Integer cdId) {
        return readAll()
                .stream()
                .filter(cd -> cd.getId() == cdId)
                .findAny()
                .get();
    }

    public void update(Integer id, Cd newCd) {
        executeStatement(new StringBuilder()
                .append("UPDATE cd SET ")
                .append("title = " + newCd.getTitle())
                .append(", genre = " + newCd.getGenre())
                .append(", year = " + newCd.getYear().toString())
                .append(", artistId = " + newCd.getArtistId().toString())
                .append(", price = " + newCd.getPrice().toString())
                .append("WHERE id =" + id.toString())
                .toString());
    }

    public void delete(Integer id) {
        executeStatement("DELETE FROM cd WHERE id = " + id.toString());
    }

    public void delete(Cd cd) {
        executeStatement("DELETE FROM cd WHERE id = " + cd.getId().toString());
    }




}
