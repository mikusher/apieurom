package com.mikusher.apieurom.repository;

import com.mikusher.apieurom.models.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResultsRepository extends PagingAndSortingRepository<Results, Long> {

    Results findById(long id);

    Results findDistinctFirstByDate(String date);

}
