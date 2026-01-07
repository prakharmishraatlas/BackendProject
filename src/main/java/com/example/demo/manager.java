// this file is used to convert the functions into SQL queries.
// Kind of like a manager handling stuff that we tell (like .save(), .find() etc)

package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // this will help to convert java syntax to complex SQL queries.
public interface manager extends JpaRepository<FetchedData, Long> {
}