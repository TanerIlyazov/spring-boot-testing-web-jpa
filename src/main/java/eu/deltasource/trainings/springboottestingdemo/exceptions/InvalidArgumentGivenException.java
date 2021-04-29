package eu.deltasource.trainings.springboottestingdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
public class InvalidArgumentGivenException extends ResponseStatusException {
    public InvalidArgumentGivenException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
