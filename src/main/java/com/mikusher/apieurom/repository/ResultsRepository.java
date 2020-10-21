package com.mikusher.apieurom.repository;

import com.mikusher.apieurom.models.Results;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRepository extends JpaRepository<Results, Long> {

    Results findById(long id);

}
