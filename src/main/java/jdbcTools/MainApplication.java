package jdbcTools;

import entities.Artist;
import entities.Cd;
import jdbcTools.ArtistRepository;
import jdbcTools.CdRepository;
import jdbcTools.Engine;

import java.sql.Connection;

public class MainApplication {

    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.registerJDBCDriver();
        Connection mysqlDbConnection = engine.getConnection("mysql");

        ArtistRepository artistRepository = new ArtistRepository(mysqlDbConnection);
        CdRepository cdRepository = new CdRepository(mysqlDbConnection);

        engine.executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS walmart;");
        engine.executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS walmart;");
        engine.executeStatement(mysqlDbConnection, "USE walmart;");
        engine.executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS walmart.artist")
                .append("(id int primary key,")
                .append("first_name text not null,")
                .append("last_name text not null,")
                .append("instrument text not null)")
                .toString());
        //Artist mariah = ;
        artistRepository.createArtist( new Artist(1,"'mariah'","'carrie'","'trumpet'"));

        engine.executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS walmart.cd")
                .append("(id int primary key,")
                .append("title text not null,")
                .append("genre text not null,")
                .append("year int not null,")
                .append("artist_id int not null,")
                .append("price int not null);")
                .toString());
        cdRepository.createCd(new Cd(1,"'ChrismasHits'", "'Holiday'",2020,1, 19) );

    }

}
