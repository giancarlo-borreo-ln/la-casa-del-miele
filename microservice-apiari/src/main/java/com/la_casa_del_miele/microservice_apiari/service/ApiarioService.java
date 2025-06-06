package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Apiario;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ApiarioService {
    List<Apiario> getAllApiari();

    Apiario getApiarioById(Long id);

    Apiario createApiario(Apiario apiario);

    Apiario updateApiario(Long id, Apiario apiario) throws AccessDeniedException;

    void deleteApiarioById(Long id);
}
