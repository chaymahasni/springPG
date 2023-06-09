package com.example.springbootapp.controller;


import com.example.springbootapp.exception.ResourceNotFoundException;
import com.example.springbootapp.model.Tutorial;
import com.example.springbootapp.model.Type;
import com.example.springbootapp.repository.TutorialRepository;
import com.example.springbootapp.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TypeController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private TypeRepository typeRepository;

  /*  @GetMapping("/tutorials/{tutorialId}/types")
    public ResponseEntity<Type> getAllTypesByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        Tutorial tutorial = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return new ResponseEntity<>(tutorial.getType(), HttpStatus.OK);
    }*/

    @GetMapping("/types/{id}")
    public ResponseEntity<Type> getTypesByTutorialId(@PathVariable(value = "id") Long id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Type with id = " + id));

        return new ResponseEntity<>(type, HttpStatus.OK);
    }
/*
    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Type> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Type commentRequest) {
        Type type = tutorialRepository.findById(tutorialId).map(tutorial -> {
            tutorial.getType().add(commentRequest);
            return typeRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return new ResponseEntity<>(type, HttpStatus.CREATED);
    }
    */


    @PostMapping("/addTypeAndTutorial")
    public ResponseEntity<Type> createTypeWithTutorials(@RequestBody Type type) {
        Type newtype = Type.builder().nom(type.getNom()).build();
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        tutorials = type.getTutorials();
        tutorials.forEach(tutorial -> {
            newtype.getTutorials().add(tutorial);
        });
        typeRepository.save(newtype);
        return new ResponseEntity<>(newtype, HttpStatus.CREATED);
    }


    @PutMapping("/types/{id}")
    public ResponseEntity<Type> updateType(@PathVariable("id") long id, @RequestBody Type typeRequest) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with id = " + id));

        type.setNom(typeRequest.getNom());
        Type updatedType = typeRepository.save(type);
        return new ResponseEntity<>(updatedType, HttpStatus.OK);
    }

    @DeleteMapping("/types/{id}")
    public ResponseEntity<HttpStatus> deleteType(@PathVariable("id") long id) {
        typeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    @DeleteMapping("/tutorials/{tutorialId}/types")
    public ResponseEntity<HttpStatus> deleteAllTypesOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        typeRepository.deleteByTutorialId(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    */
}
