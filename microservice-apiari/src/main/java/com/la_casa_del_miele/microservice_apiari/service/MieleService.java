package com.la_casa_del_miele.microservice_apiari.service;

import com.la_casa_del_miele.microservice_apiari.model.Miele;

import java.util.List;

public interface MieleService {
    List<Miele> getAllMiele();
    Miele findMieleById(Long id);
    Miele createMiele(Miele miele);
    Miele upgradeMiele(Long id, Miele mieleVecchio);
    void deleteMieleById(long id);
}
