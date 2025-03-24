package com.la_casa_del_miele.microservice_apiari;

import com.la_casa_del_miele.microservice_apiari.model.Apicoltore;
import com.la_casa_del_miele.microservice_apiari.repository.ApicoltoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApicoltoreControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApicoltoreRepository apicoltoreRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        // Pulizia del DB prima di ogni test
        apicoltoreRepository.deleteAll();

        // Costruiamo l'URL di base, es. "http://localhost:12345/api/apicoltori"
        baseUrl = "http://localhost:" + port + "/api/apicoltori";
    }

    @Test
    void testCreateAndGetApicoltore() {
        // Creiamo un Apicoltore senza settare l'ID (autogenerato)
        Apicoltore input = new Apicoltore();
        input.setpIva("12345678901");
        input.setEmail("test@example.com");
        input.setPassword("secret");
        input.setTelefono("1234567890");
        input.setNome("Mario");
        input.setCognome("Rossi");
        input.setRagioneSociale("Apicoltura Rossi");

        // 1) Invio POST -> crea Apicoltore
        ResponseEntity<Apicoltore> postResponse = restTemplate.postForEntity(
                baseUrl, input, Apicoltore.class
        );
        // Verifico 201 Created
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Controllo che in DB ci sia 1 record
        List<Apicoltore> all = apicoltoreRepository.findAll();
        assertThat(all).hasSize(1);

        // Recupero l'ID generato dal DB
        Long createdId = all.get(0).getId();

        // 2) Invio GET -> recupero Apicoltore per ID
        ResponseEntity<Apicoltore> getResponse = restTemplate.getForEntity(
                baseUrl + "/" + createdId,
                Apicoltore.class
        );
        // Verifico 200 OK
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Apicoltore found = getResponse.getBody();
        assertThat(found.getpIva()).isEqualTo("12345678901");
        assertThat(found.getNome()).isEqualTo("Mario");
    }

    @Test
    void testUpdateApicoltore() {
        // Creo e salvo un Apicoltore iniziale
        Apicoltore existing = new Apicoltore();
        existing.setpIva("11111111111");
        existing.setEmail("old@example.com");
        existing.setPassword("oldpass");
        existing.setNome("OldName");
        existing.setCognome("OldSurname");
        existing.setRagioneSociale("OldRagSociale");
        apicoltoreRepository.save(existing);

        // Aggiorno i campi
        Apicoltore updated = new Apicoltore();
        updated.setpIva("11111111111"); // P.IVA invariata, supponiamo
        updated.setEmail("new@example.com");
        updated.setPassword("newpass");
        updated.setNome("NewName");
        updated.setCognome("NewSurname");
        updated.setRagioneSociale("NewRagSociale");

        // Invio PUT -> /api/apicoltori/{id}
        ResponseEntity<Apicoltore> putResponse = restTemplate.exchange(
                baseUrl + "/" + existing.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(updated),
                Apicoltore.class
        );
        // Verifico 200 OK
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Controllo che il DB sia aggiornato
        Apicoltore inDb = apicoltoreRepository.findById(existing.getId()).orElseThrow();
        assertThat(inDb.getEmail()).isEqualTo("new@example.com");
        assertThat(inDb.getNome()).isEqualTo("NewName");
    }

    @Test
    void testDeleteApicoltore() {
        // Creo e salvo un Apicoltore
        Apicoltore a = new Apicoltore();
        a.setpIva("99999999999");
        a.setEmail("delete@example.com");
        a.setPassword("deletepass");
        a.setNome("ToDelete");
        a.setCognome("ToDelete");
        a.setRagioneSociale("DeleteRagSociale");
        a = apicoltoreRepository.save(a);

        // Invio DELETE -> /api/apicoltori/{id}
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                baseUrl + "/" + a.getId(),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );
        // Verifico 204 NO_CONTENT
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // Controllo che non esista più in DB
        assertThat(apicoltoreRepository.existsById(a.getId())).isFalse();
    }

    @Test
    void testGetAllApicoltori() {
        // Creo due Apicoltori
        Apicoltore a1 = new Apicoltore();
        a1.setpIva("11111111111");
        a1.setEmail("email1@example.com");
        a1.setPassword("pass1");
        a1.setNome("Nome1");
        a1.setCognome("Cognome1");
        a1.setRagioneSociale("RagSoc1");
        apicoltoreRepository.save(a1);

        Apicoltore a2 = new Apicoltore();
        a2.setpIva("22222222222");
        a2.setEmail("email2@example.com");
        a2.setPassword("pass2");
        a2.setNome("Nome2");
        a2.setCognome("Cognome2");
        a2.setRagioneSociale("RagSoc2");
        apicoltoreRepository.save(a2);

        // Invio GET -> /api/apicoltori
        ResponseEntity<Apicoltore[]> response = restTemplate.getForEntity(
                baseUrl,
                Apicoltore[].class
        );
        // Se ci sono record, ci aspettiamo 200 OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // Verifichiamo che ci siano 2 Apicoltori
        assertThat(response.getBody()).hasSize(2);
    }
}
