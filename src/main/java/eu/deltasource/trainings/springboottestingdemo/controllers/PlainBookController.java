//package eu.deltasource.trainings.springboottestingdemo.controllers;
//
//import eu.deltasource.trainings.springboottestingdemo.exceptions.BookNotAvailableException;
//import eu.deltasource.trainings.springboottestingdemo.exceptions.InvalidArgumentGivenException;
//import eu.deltasource.trainings.springboottestingdemo.model.Book;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import static eu.deltasource.trainings.springboottestingdemo.model.Book.newBook;
//
///**
// * Created by Taner - Delta Source Bulgaria on 22.04.21.
// */
//public class PlainBookController {
//
//    private final List<Book> books = Arrays.asList(
//            newBook("Clean Code", "Educational"),
//            newBook("Clean Coder", "Educational"),
//            newBook("Linux Bible", "Religion")
//    );
//
//    public List<Book> getAllBooks() {
//        return books;
//    }
//
//    public Book getBookByName(String bookName) {
//        if (Objects.isNull(bookName) || bookName.trim().isEmpty()) {
//            throw new InvalidArgumentGivenException("Invalid book name");
//        }
//        Optional<Book> optionalBook = books.stream()
//                .filter(book -> book.getName().equalsIgnoreCase(bookName))
//                .findFirst();
//
//        return optionalBook.orElseThrow(BookNotAvailableException::new);
//    }
//}
