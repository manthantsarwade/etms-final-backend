package com.etms.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.etms.pojos.Person;
import com.etms.pojos.PersonStatus;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	  Optional<Person> findByEmail(String email);
	  boolean existsByEmail(String email);
	  List<Person> findByStatus(PersonStatus status);
	List<Person> findByRole(String string);
}
