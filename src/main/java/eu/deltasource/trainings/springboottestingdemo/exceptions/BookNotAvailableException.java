package eu.deltasource.trainings.springboottestingdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
public class BookNotAvailableException extends ResponseStatusException {
    public BookNotAvailableException() {
        super(HttpStatus.NOT_FOUND);
    }
}
