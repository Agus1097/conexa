package com.conexa.challenge.controller;

import com.conexa.challenge.model.*;
import com.conexa.challenge.service.StarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/starships")
public class StarshipController {

    private final StarshipService starshipService;

    @Autowired
    public StarshipController(StarshipService starshipService) {
        this.starshipService = starshipService;
    }

    @GetMapping
    public ResponseEntity<SWApiResponsePaged<Starship>> getPagedStarships(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                          @RequestParam(name = "limit", defaultValue = "10") int limit) {
        return ResponseEntity.ok(starshipService.getStarshipsPaged(page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SWApiResponse<StarshipDetail>> getPeopleById(@PathVariable int id) {
        return ResponseEntity.ok(starshipService.getStarshipById(id));
    }

    @GetMapping("/")
    public ResponseEntity<SWApiResponseList<StarshipDetail>> getPeopleByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(starshipService.getStarshipByName(name));
    }
}
