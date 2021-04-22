package eu.deltasource.trainings.springboottestingdemo.model;

import eu.deltasource.trainings.springboottestingdemo.exceptions.InvalidArgumentGivenException;
import lombok.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
@Data
@RequiredArgsConstructor
public class Book {
    private final String name;
    private final List<String> tags;

    public static Book newBook(String bookName, String... tags) {
        if (invalidBookParameters(bookName, tags)) {
            throw new InvalidArgumentGivenException("Invalid parameters for instantiating book");
        }
        return new Book(bookName, Arrays.asList(tags));
    }

    private static boolean invalidBookParameters(String bookName, String[] tags) {
        return bookName == null || bookName.trim().isEmpty() || tags == null || tags.length < 1;
    }

}
