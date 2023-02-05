package ua.prof.hw5.repo;

import org.springframework.data.repository.CrudRepository;
import ua.prof.hw5.models.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
