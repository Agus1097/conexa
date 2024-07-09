package com.conexa.challenge.controller;

import com.conexa.challenge.model.*;
import com.conexa.challenge.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public ResponseEntity<SWApiResponsePaged<People>> getPagedPeople(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                     @RequestParam(name = "limit", defaultValue = "10") int limit) {
        return ResponseEntity.ok(peopleService.getPagedPeople(page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SWApiResponse<PeopleDetail>> getPeopleById(@PathVariable int id) {
        return ResponseEntity.ok(peopleService.getPeopleById(id));
    }

    @GetMapping("/")
    public ResponseEntity<SWApiResponseList<PeopleDetail>> getPeopleByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(peopleService.getPeopleByName(name));
    }
}
