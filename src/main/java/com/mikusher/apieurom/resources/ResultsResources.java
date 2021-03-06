package com.mikusher.apieurom.resources;

import com.mikusher.apieurom.models.Results;
import com.mikusher.apieurom.repository.ResultsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Euro Million Value")
@CrossOrigin(origins = "*")
public class ResultsResources {
    @Autowired
    ResultsRepository resultsRepository;

    @GetMapping("/results")
    @ApiOperation(value = "Show all results")
    public ResponseEntity<?> listResults(Pageable pageable){
        return new ResponseEntity<>(resultsRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("results/{id}")
    @ApiOperation(value = "Show one result, get result by Id")
    public Results resultById(@PathVariable(value = "id") long id){
        return resultsRepository.findById(id);
    }

    @GetMapping("date/{date}")
    @ApiOperation(value = "Show one result, get result by date")
    public Results resultByDate(@PathVariable(value = "date") String date){
        return resultsRepository.findDistinctFirstByDate(date);
    }

    //new values is save automatically
    @ApiIgnore
    @PostMapping("/results")
    @ApiOperation(value = "Save, new result in data base")
    public Results saveNewResult(@RequestBody Results results){
        return resultsRepository.save(results);
    }

    @DeleteMapping("/results")
    @ApiOperation(value = "Delete result, information in body", hidden = true)
    public void deleteResult(@RequestBody Results results){
        resultsRepository.delete(results);
    }

    @ApiIgnore
    @DeleteMapping("/results/{id}")
    @ApiOperation(value = "Delete result, information by Id")
    public void deleteResultById(@PathVariable(value = "id") long id){
        resultsRepository.deleteById(id);
    }

    @PutMapping("/results")
    @ApiOperation(value = "Change value for result", hidden = true)
    public Results updateResult(@RequestBody Results results){
        return resultsRepository.save(results);
    }
}
