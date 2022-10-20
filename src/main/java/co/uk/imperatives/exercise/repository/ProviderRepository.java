package co.uk.imperatives.exercise.repository;

import co.uk.imperatives.exercise.exception.DatabaseException;
import co.uk.imperatives.exercise.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findByName(String name) throws DatabaseException;
}