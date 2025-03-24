package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Apiario;
import com.la_casa_del_miele.microservice_apiari.repository.ApiarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiarioServiceImpl implements ApiarioService{
    private final ApiarioRepository apiarioRepository;

    public ApiarioServiceImpl(ApiarioRepository apiarioRepository){
        this.apiarioRepository =  apiarioRepository;
    }


    @Override
    public List<Apiario> getAllApiari() {
        return apiarioRepository.findAll();
    }

    @Override
    public Apiario getApiarioById(Long id) {
        return apiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apiario non trovato con id: " + id));
    }

    @Override
    public Apiario createApiario(Apiario apiario) {
        return apiarioRepository.save(apiario);
    }

    @Override
    public Apiario updateApiario(Long id, Apiario apiario) {
        Apiario apiarioNuovo = apiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apiario non trovato con id: " + id));
        apiarioNuovo.setAnno(apiario.getAnno());
        apiarioNuovo.setApicoltore(apiario.getApicoltore());
        apiarioNuovo.setCollocazione(apiario.getCollocazione());
        apiarioNuovo.setMiele(apiario.getMiele());
        apiarioNuovo.setNumArnie(apiario.getNumArnie());
        apiarioNuovo.setQta(apiario.getQta());

        return apiarioRepository.save(apiarioNuovo);
    }

    @Override
    public void deleteApiarioById(Long id) {
        apiarioRepository.deleteById(id);
    }
}
