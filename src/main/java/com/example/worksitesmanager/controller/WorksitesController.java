package com.example.worksitesmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.worksitesmanager.entity.WorksitesRequest;
import com.example.worksitesmanager.model.Worksites;
import com.example.worksitesmanager.service.WorksitesService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/worksites")
public class WorksitesController {
    private static final Logger logger = LoggerFactory.getLogger(WorksitesController.class);
    @Autowired
    private WorksitesService worksitesService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWorksitesById(@PathVariable("id") Long id) {
        var a = 1;
        try {
            Worksites worksites = worksitesService.getWorksitesById(id);
            return new ResponseEntity<>(worksites, HttpStatus.OK);
        } catch (NotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            // 用見直し
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllWorksites() {
        List<Worksites> worksitesList = worksitesService.getAllWorksites();
        return new ResponseEntity<>(worksitesList, HttpStatus.OK);
    }

    @GetMapping("/updated/from/{strFrom}/to/{strTo}")
    public ResponseEntity<Object> getWorksitesByUpdatedBetween(@PathVariable String strFrom, @PathVariable String strTo) {
        List<Worksites> worksitesList = worksitesService.getWorksitesByUpdatedBetween(strFrom, strTo);
        return new ResponseEntity<>(worksitesList, HttpStatus.OK);
    }

    @GetMapping("/maxid")
    public ResponseEntity<Object> getMaxIdWorksites() {
        try {
            Worksites worksites = worksitesService.getMaxIdWorksites();
            return new ResponseEntity<>(worksites, HttpStatus.OK);
        } catch (NotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createWorksites(@RequestBody WorksitesRequest worksitesRequest) {
        int aa = 1;
        Worksites worksites = worksitesService.createWorksites(worksitesRequest);
        return new ResponseEntity<>(worksites, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWorksites(@PathVariable("id") Long id, @RequestBody WorksitesRequest worksitesRequest) {
        try {
            Worksites worksites = worksitesService.updateWorksites(id, worksitesRequest);
            return new ResponseEntity<>(worksites, HttpStatus.OK);
        } catch (NotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWorksites(@PathVariable("id") Long id) {
        try {
            Worksites worksites = worksitesService.deleteWorksites(id);
            return new ResponseEntity<>(worksites, HttpStatus.OK);
        } catch (NotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
