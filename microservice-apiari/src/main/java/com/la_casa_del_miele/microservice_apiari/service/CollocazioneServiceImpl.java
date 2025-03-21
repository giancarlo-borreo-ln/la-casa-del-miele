package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Collocazione;
import com.la_casa_del_miele.microservice_apiari.repository.CollocazioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollocazioneServiceImpl implements CollocazioneService{

   private final CollocazioneRepository collocazioneRepository;

   public CollocazioneServiceImpl(CollocazioneRepository collocazioneRepository){
       this.collocazioneRepository = collocazioneRepository;
   }


    @Override
    public List<Collocazione> getAllCollocazioni() {
        return collocazioneRepository.findAll();
    }

    @Override
    public Collocazione getCollocazioneById(long id){
       return collocazioneRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Apicoltore non trovato con id: " + id));
    }

    @Override
    public Collocazione createCollocazione(Collocazione collocazione) {
        return collocazioneRepository.save(collocazione);
    }

    @Override
    public Collocazione updateCollocazione(Collocazione collocazione) {
        return null;
    }

    @Override
    public void deleteCollocazione(long id) {
        collocazioneRepository.deleteById(id);
    }
}
