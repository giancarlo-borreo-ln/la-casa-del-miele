package com.la_casa_del_miele.microservice_apiari.controller;

import com.la_casa_del_miele.microservice_apiari.model.Apicoltore;
import com.la_casa_del_miele.microservice_apiari.service.ApicoltoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apicoltori")
public class ApicoltoreController {

    private final ApicoltoreService apicoltoreService;

    public ApicoltoreController(ApicoltoreService apicoltoreService) {
        this.apicoltoreService = apicoltoreService;
    }

    @GetMapping
    public ResponseEntity<List<Apicoltore>> getAllApicoltori() {
        List<Apicoltore> apicoltori = apicoltoreService.getAllApicoltori();
        return apicoltori.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(apicoltori);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apicoltore> getApicoltoreById(@PathVariable Long id) {
        try {
            Apicoltore found = apicoltoreService.getApicoltoreById(id);
            return ResponseEntity.ok(found);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Apicoltore> createApicoltore(@RequestBody Apicoltore apicoltore) {
        Apicoltore created = apicoltoreService.createApicoltore(apicoltore);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apicoltore> updateApicoltore(
            @PathVariable Long id,
            @RequestBody Apicoltore updated
    ) {
        try {
            Apicoltore result = apicoltoreService.updateApicoltore(id, updated);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApicoltore(@PathVariable Long id) {
        try {
            apicoltoreService.deleteApicoltoreById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
