package eu.deltasource.trainings.springboottestingdemo.controllers;//package eu.deltasource.trainings.springboottestingdemo.controllers;

import eu.deltasource.trainings.springboottestingdemo.SpringBootTestingDemoApplication;
import eu.deltasource.trainings.springboottestingdemo.exceptions.BookNotAvailableException;
import eu.deltasource.trainings.springboottestingdemo.exceptions.InvalidArgumentGivenException;
import eu.deltasource.trainings.springboottestingdemo.model.Book;
import eu.deltasource.trainings.springboottestingdemo.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringBootTestingDemoApplication.class)
public class BookControllerWithRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestBookController bookController;

    @BeforeEach
    public void persistBooks() {
        bookRepository.saveAll(
                Arrays.asList(
                        new Book("Clean Code", "Educational"),
                        new Book("Code", "Educational"),
                        new Book("Linux Bible", "Religion")
                )
        );
    }

    @Test
    void getAllBooks() {
        //given
        List<Book> expectedBooks = Arrays.asList(
                new Book(1L,"Clean Code", "Educational"),
                new Book(2L, "Code", "Educational"),
                new Book(3L, "Linux Bible", "Religion")
        );

        //when
        List<Book> actualBooks = bookController.getAllBooks();

        //then
        assertNotNull(actualBooks);
        assertTrue(expectedBooks.containsAll(actualBooks));
    }

    @Test
    void getBookByName__returnsBook() {
        //given
        Book expectedBook = new Book(1L, "Clean Code", "Educational");

        //when
        Book actualBook = bookController.getBookByName(expectedBook.getName());

        //then
        assertNotNull(actualBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void getBookByName__NullArgument__throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentGivenException.class, () -> bookController.getBookByName(null));
    }

    @Test
    void getBookByName__MissingArgument__throwsBookNotAvailableException() {
        assertThrows(BookNotAvailableException.class, () -> bookController.getBookByName("Dirty Code"));
    }

}