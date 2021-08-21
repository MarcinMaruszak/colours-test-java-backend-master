package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity(coloursRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Colour> addColor(@RequestBody Colour colour){
        coloursRepository.save(colour);
        return ResponseEntity.ok(colour);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colour> getColor(@PathVariable long id){
        Optional<Colour> colourOptional = coloursRepository.findById(id);
        return colourOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
