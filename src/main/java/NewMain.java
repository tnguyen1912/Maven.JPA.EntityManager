import entities.Artist;
import entities.Person;
import services.PersonService;

import java.util.List;
import java.util.stream.Stream;

public class NewMain {

    public static void main(String[] args) {
        PersonService personService = new PersonService();

        Person person1 = new Person(10,"Peter","Pirelli");
        personService.update(1,person1);
    }
}
