package co.uk.imperatives.exercise.repository;

import co.uk.imperatives.exercise.exception.DatabaseException;
import co.uk.imperatives.exercise.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByCity(String city) throws DatabaseException;

    Location findByBorough(String borough) throws DatabaseException;

    Location findByCityAndBorough(String name, String borough) throws DatabaseException;
}