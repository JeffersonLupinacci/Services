package com.jeffersonlupinacci.app.data.repository;

import com.jeffersonlupinacci.app.data.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Person Repository
 *
 * @author jeffersonlu
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
