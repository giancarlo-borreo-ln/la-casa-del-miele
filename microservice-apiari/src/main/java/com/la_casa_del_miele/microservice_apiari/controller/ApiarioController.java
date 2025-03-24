package com.la_casa_del_miele.microservice_apiari.controller;

import com.la_casa_del_miele.microservice_apiari.model.Apiario;
import com.la_casa_del_miele.microservice_apiari.service.ApiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apiario")
public class ApiarioController {
    private final ApiarioService apiarioService;

    public ApiarioController(ApiarioService apiarioService){
        this.apiarioService = apiarioService;
    }

    @GetMapping
    public ResponseEntity<List<Apiario>> getAllApiari(){
        List<Apiario> apiario = apiarioService.getAllApiari();
        return apiario.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(apiario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apiario> getApiarioById(@PathVariable Long id){
        try{
            Apiario found = apiarioService.getApiarioById(id);
            return ResponseEntity.ok(found);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Apiario> createApiario(@RequestBody Apiario apiario){
        Apiario created = apiarioService.createApiario(apiario);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apiario> updateApiario(
            @PathVariable Long id,
            @RequestBody Apiario apiario
    ){
        try{
            Apiario result = apiarioService.updateApiario(id, apiario);
            return ResponseEntity.ok(result);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApiario(@PathVariable Long id){
        try{
            apiarioService.deleteApiarioById(id);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
