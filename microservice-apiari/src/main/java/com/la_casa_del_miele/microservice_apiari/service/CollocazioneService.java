package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Collocazione;

import java.util.List;

public interface CollocazioneService {
    List<Collocazione> getAllCollocazioni();

    Collocazione getCollocazioneById(long id);

    Collocazione createCollocazione(Collocazione collocazione);

    Collocazione updateCollocazione(Collocazione collocazione);

    void deleteCollocazione(long id);

}
