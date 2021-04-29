package eu.deltasource.trainings.springboottestingdemo.repositories;

import eu.deltasource.trainings.springboottestingdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Taner - Delta Source Bulgaria on 22.04.21.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
