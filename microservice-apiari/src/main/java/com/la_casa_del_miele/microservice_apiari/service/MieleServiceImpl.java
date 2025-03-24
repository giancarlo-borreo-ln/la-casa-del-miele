package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Miele;
import com.la_casa_del_miele.microservice_apiari.repository.MieleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MieleServiceImpl implements MieleService {
    private final MieleRepository mieleRepository;

    public MieleServiceImpl(MieleRepository mieleRepository){
        this.mieleRepository = mieleRepository;
    }


    @Override
    public List<Miele> getAllMiele() {
        return mieleRepository.findAll();
    }

    @Override
    public Miele findMieleById(Long id) {
        return mieleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miele non trovato con id: " + id));
    }

    @Override
    public Miele createMiele(Miele miele) {
        return mieleRepository.save(miele);
    }

    @Override
    public Miele upgradeMiele(Long id, Miele mieleVecchio) {
        Miele nuovoMiele = mieleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miele non trovato con id: " + id));
        nuovoMiele.setNome(mieleVecchio.getNome());
        nuovoMiele.setTipo(mieleVecchio.getTipo());

        return nuovoMiele;
    }

    @Override
    public void deleteMieleById(long id) {
        mieleRepository.deleteById(id);
    }
}
