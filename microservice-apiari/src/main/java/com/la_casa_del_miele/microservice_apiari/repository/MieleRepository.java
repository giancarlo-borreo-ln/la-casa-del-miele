package com.la_casa_del_miele.microservice_apiari.repository;

import com.la_casa_del_miele.microservice_apiari.model.Miele;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MieleRepository extends JpaRepository<Miele, Long> {
}
