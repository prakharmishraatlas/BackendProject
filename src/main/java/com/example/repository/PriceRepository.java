// this file is used to convert the functions into SQL queries.
// Kind of like a manager handling stuff that we tell (like .save(), .find() etc)

package com.example.repository;
import com.example.model.FetchedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<FetchedData, Long> {
}