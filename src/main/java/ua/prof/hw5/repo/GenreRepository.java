package ua.prof.hw5.repo;

import org.springframework.data.repository.CrudRepository;
import ua.prof.hw5.models.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
