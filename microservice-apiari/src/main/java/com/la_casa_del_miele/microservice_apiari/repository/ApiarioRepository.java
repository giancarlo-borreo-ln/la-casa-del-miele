package com.la_casa_del_miele.microservice_apiari.repository;

import com.la_casa_del_miele.microservice_apiari.model.Apiario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApiarioRepository extends JpaRepository<Apiario, Long> {

}
