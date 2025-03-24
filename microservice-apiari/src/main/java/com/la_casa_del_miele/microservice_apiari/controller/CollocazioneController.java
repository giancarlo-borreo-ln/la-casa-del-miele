package com.la_casa_del_miele.microservice_apiari.controller;

import com.la_casa_del_miele.microservice_apiari.model.Collocazione;
import com.la_casa_del_miele.microservice_apiari.service.CollocazioneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collocazione")
public class CollocazioneController {
    private final CollocazioneService collocazioneService;

    public CollocazioneController(CollocazioneService collocazioneService){
        this.collocazioneService = collocazioneService;
    }

    @GetMapping
    public ResponseEntity<List<Collocazione>> getAllCollocazioni(){
        List<Collocazione> collocazioni = collocazioneService.getAllCollocazioni();
        return collocazioni.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(collocazioni);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Collocazione> getCollocazioneById(@PathVariable Long id){
        try{
            Collocazione found = collocazioneService.getCollocazioneById(id);
            return ResponseEntity.ok(found);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Collocazione> createCollocazione(@RequestBody Collocazione collocazione){
        Collocazione created = collocazioneService.createCollocazione(collocazione);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collocazione> updateCollocazione(
            @PathVariable Long id,
            @RequestBody Collocazione updated
    ){
        try{
            Collocazione result = collocazioneService.updateCollocazione(id, updated);
            return ResponseEntity.ok(result);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollocazione(@PathVariable Long id){
        try{
            collocazioneService.deleteCollocazione(id);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

}
