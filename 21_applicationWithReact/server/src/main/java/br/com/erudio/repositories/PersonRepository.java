package br.com.erudio.repositories;

import br.com.erudio.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query("update Person p set p.enabled = false where p.id = :id")
    void disablePerson(@Param("id") Long id);

    @Query("select p from Person p where p.firstName ilike lower(concat('%',:firstName,'%') ) ")
    Page<Person> findPersonsByName(@Param("firstName") String firstName, Pageable pageable);
}
