package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Apicoltore;
import java.util.List;

public interface ApicoltoreService {

    List<Apicoltore> getAllApicoltori();

    Apicoltore getApicoltoreById(Long id);

    Apicoltore createApicoltore(Apicoltore apicoltore);

    Apicoltore updateApicoltore(Long id, Apicoltore apicoltore);

    void deleteApicoltoreById(Long id);
}
