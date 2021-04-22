package eu.deltasource.trainings.springboottestingdemo.exceptions;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
public class InvalidArgumentGivenException extends RuntimeException {
    public InvalidArgumentGivenException(String message) {
        super(message);
    }
}
