package eu.deltasource.trainings.springboottestingdemo.controllers;

import eu.deltasource.trainings.springboottestingdemo.exceptions.BookNotAvailableException;
import eu.deltasource.trainings.springboottestingdemo.exceptions.InvalidArgumentGivenException;
import eu.deltasource.trainings.springboottestingdemo.model.Book;
import eu.deltasource.trainings.springboottestingdemo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
@RequiredArgsConstructor
@RestController("/books")
public class RestBookController {

    private final BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.getBooks();
    }

    @GetMapping(params = "name")
    public Book getBookByName(@RequestParam String bookName) {
        if (Objects.isNull(bookName) || bookName.trim().isEmpty()) {
            throw new InvalidArgumentGivenException("Invalid book name");
        }
        Optional<Book> optionalBook = bookRepository.getBooks()
                .stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .findFirst();

        return optionalBook.orElseThrow(BookNotAvailableException::new);
    }

    @GetMapping(params = "pattern")
    public String getBookNameByPattern(@RequestParam String pattern) {
        if (Objects.isNull(pattern) || pattern.trim().isEmpty()) {
            throw new InvalidArgumentGivenException("Invalid book name");
        }
        Optional<String> optionalBook = bookRepository.getBooks()
                .stream()
                .map(Book::getName)
                .filter(bookName -> bookName.contains(pattern))
                .findFirst();

        return optionalBook.orElseThrow(BookNotAvailableException::new);
    }
}
