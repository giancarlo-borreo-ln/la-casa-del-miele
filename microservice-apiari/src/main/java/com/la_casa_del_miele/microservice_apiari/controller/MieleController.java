package com.la_casa_del_miele.microservice_apiari.controller;

import com.la_casa_del_miele.microservice_apiari.model.Miele;
import com.la_casa_del_miele.microservice_apiari.service.MieleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miele")
public class MieleController {

    private final MieleService mieleService;

    public MieleController(MieleService mieleService){
        this.mieleService = mieleService;
    }

    @GetMapping
    public ResponseEntity<List<Miele>> getAllMieli(){
        List<Miele> miele = mieleService.getAllMiele();
        return miele.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(miele);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Miele> getMieleById(@PathVariable Long id){
        try{
            Miele miele = mieleService.findMieleById(id);
            return ResponseEntity.ok(miele);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Miele> createMiele(@RequestBody Miele miele){
        Miele created = mieleService.createMiele(miele);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Miele> updateMiele(
            @PathVariable Long id,
            @RequestBody Miele updated
    ){
        try{
            Miele result = mieleService.upgradeMiele(id, updated);
            return ResponseEntity.ok(result);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiele(@PathVariable Long id){
        try{
            mieleService.deleteMieleById(id);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

}
