package com.la_casa_del_miele.microservice_apiari.repository;

import com.la_casa_del_miele.microservice_apiari.model.Apicoltore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApicoltoreRepository extends JpaRepository<Apicoltore, Long> {
	Apicoltore findByEmail(String email);
}
