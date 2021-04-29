package eu.deltasource.trainings.springboottestingdemo.model;

import eu.deltasource.trainings.springboottestingdemo.exceptions.InvalidArgumentGivenException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String[] tags;

    public Book(String name, String ...tags) {
        if (invalidBookParameters(name, tags)) {
            throw new InvalidArgumentGivenException("Invalid parameters for instantiating book");
        }
        this.name = name;
        this.tags = tags;
    }

    public Book(Long id, String name, String ...tags) {
        if (invalidBookParameters(name, tags)) {
            throw new InvalidArgumentGivenException("Invalid parameters for instantiating book");
        }
        this.id = id;
        this.name = name;
        this.tags = tags;
    }

    private static boolean invalidBookParameters(String bookName, String[] tags) {
        return bookName == null || bookName.trim().isEmpty() || tags == null || tags.length < 1;
    }

}
