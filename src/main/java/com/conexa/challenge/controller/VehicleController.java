package com.conexa.challenge.controller;

import com.conexa.challenge.model.*;
import com.conexa.challenge.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<SWApiResponsePaged<Vehicle>> getPagedPeople(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                      @RequestParam(name = "limit", defaultValue = "10") int limit) {
        return ResponseEntity.ok(vehicleService.getPagedVehicles(page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SWApiResponse<VehicleDetail>> getPeopleById(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @GetMapping("/")
    public ResponseEntity<SWApiResponseList<VehicleDetail>> getPeopleByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(vehicleService.getVehicleByName(name));
    }
}
