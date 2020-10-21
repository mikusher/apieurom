package com.mikusher.apieurom.resources;

import com.mikusher.apieurom.models.Results;
import com.mikusher.apieurom.repository.ResultsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Results> listResults(){
        return resultsRepository.findAll();
    }

    @GetMapping("results/{id}")
    @ApiOperation(value = "Show one result, get result by Id")
    public Results resultById(@PathVariable(value = "id") long id){
        return resultsRepository.findById(id);
    }

    @PostMapping("/results")
    @ApiOperation(value = "Save, new result in data base")
    public Results saveNewResult(@RequestBody Results results){
        return resultsRepository.save(results);
    }

    @DeleteMapping("/results")
    @ApiOperation(value = "Delete result, information in body")
    public void deleteResult(@RequestBody Results results){
        resultsRepository.delete(results);
    }

    @DeleteMapping("/results/{id}")
    @ApiOperation(value = "Delete result, information by Id")
    public void deleteResultById(@PathVariable(value = "id") long id){
        resultsRepository.deleteById(id);
    }

    @PutMapping("/results")
    @ApiOperation(value = "Change value for result")
    public Results updateResult(@RequestBody Results results){
        return resultsRepository.save(results);
    }
}
