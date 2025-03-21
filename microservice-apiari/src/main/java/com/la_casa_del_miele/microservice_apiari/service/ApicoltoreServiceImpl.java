package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Apicoltore;
import com.la_casa_del_miele.microservice_apiari.repository.ApicoltoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApicoltoreServiceImpl implements ApicoltoreService{

    private final ApicoltoreRepository apicoltoreRepository;

    public ApicoltoreServiceImpl(ApicoltoreRepository apicoltoreRepository) {
        this.apicoltoreRepository = apicoltoreRepository;
    }


    @Override
    public List<Apicoltore> getAllApicoltori() {
        return apicoltoreRepository.findAll();
    }

    @Override
    public Apicoltore getApicoltoreById(Long id) {
        return apicoltoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apicoltore non trovato con id: " + id));
    }

    @Override
    public Apicoltore createApicoltore(Apicoltore apicoltore) {
        return apicoltoreRepository.save(apicoltore);
    }


    @Override
    public Apicoltore updateApicoltore(Long id, Apicoltore updatedApicoltore) {
        Apicoltore existing = apicoltoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apicoltore non trovato con id: " + id));

        existing.setpIva(updatedApicoltore.getpIva());
        existing.setEmail(updatedApicoltore.getEmail());
        existing.setPassword(updatedApicoltore.getPassword());
        existing.setTelefono(updatedApicoltore.getTelefono());
        existing.setNome(updatedApicoltore.getNome());
        existing.setCognome(updatedApicoltore.getCognome());
        existing.setRagioneSociale(updatedApicoltore.getRagioneSociale());

        return apicoltoreRepository.save(existing);
    }

    @Override
    public void deleteApicoltore(Long id) {
        apicoltoreRepository.deleteById(id);
    }
}
