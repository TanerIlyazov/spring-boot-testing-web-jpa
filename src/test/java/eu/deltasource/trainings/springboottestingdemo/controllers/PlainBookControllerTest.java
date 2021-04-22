package eu.deltasource.trainings.springboottestingdemo.controllers;

import eu.deltasource.trainings.springboottestingdemo.exceptions.BookNotAvailableException;
import eu.deltasource.trainings.springboottestingdemo.exceptions.InvalidArgumentGivenException;
import eu.deltasource.trainings.springboottestingdemo.model.Book;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static eu.deltasource.trainings.springboottestingdemo.model.Book.newBook;
import static org.junit.jupiter.api.Assertions.*;

class PlainBookControllerTest {

    private final PlainBookController bookController = new PlainBookController();

    @Test
    void getAllBooks() {
        //given
        int expectedBookCount = 3;
        List<Book> expectedBooks = Arrays.asList(
                newBook("Clean Code", "Educational"),
                newBook("Clean Coder", "Educational"),
                newBook("Linux Bible", "Religion")
        );

        //when
        List<Book> actualBooks = bookController.getAllBooks();

        //then
        assertNotNull(actualBooks);
        assertEquals(expectedBookCount, actualBooks.size());
        assertTrue(expectedBooks.containsAll(actualBooks));
    }

    @Test
    void getBookByName__returnsBook() {
        //given
        Book expectedBook = newBook("Clean Code", "Educational");

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