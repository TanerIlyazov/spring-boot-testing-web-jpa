package eu.deltasource.trainings.springboottestingdemo.repositories;

import eu.deltasource.trainings.springboottestingdemo.model.Book;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static eu.deltasource.trainings.springboottestingdemo.model.Book.newBook;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
@Getter
@Repository
public class BookRepository {

    private final List<Book> books = Arrays.asList(
            newBook("Clean Code", "Educational"),
            newBook("Code", "Educational"),
            newBook("Linux Bible", "Religion")
    );


}
