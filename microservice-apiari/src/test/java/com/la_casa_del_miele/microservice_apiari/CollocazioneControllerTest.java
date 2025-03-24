package com.la_casa_del_miele.microservice_apiari;

import com.la_casa_del_miele.microservice_apiari.model.Collocazione;
import com.la_casa_del_miele.microservice_apiari.repository.CollocazioneRepository;
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
class CollocazioneControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CollocazioneRepository collocazioneRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        // Svuota il DB prima di ogni test
        collocazioneRepository.deleteAll();

        // Imposta la base URL, es. "http://localhost:12345/api/collocazione"
        baseUrl = "http://localhost:" + port + "/api/collocazione";
    }

    @Test
    void testCreateAndGetCollocazione() {
        // 1) Creo una Collocazione senza settare l'ID
        Collocazione input = new Collocazione();
        input.setLocalita("Località Test");
        input.setComune("ComuneTest");
        input.setRegione("RegioneTest");
        input.setProvincia("RT");

        // 2) Faccio POST su /api/collocazione
        ResponseEntity<Collocazione> postResponse = restTemplate.postForEntity(
                baseUrl,
                input,
                Collocazione.class
        );
        // Verifico che la risposta sia 201 Created
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // 3) Controllo che nel DB ci sia 1 record
        List<Collocazione> all = collocazioneRepository.findAll();
        assertThat(all).hasSize(1);

        // Recupero l'ID autogenerato
        Long generatedId = all.get(0).getId();

        // 4) GET /api/collocazione/{id} per verificare che esista
        ResponseEntity<Collocazione> getResponse = restTemplate.getForEntity(
                baseUrl + "/" + generatedId,
                Collocazione.class
        );
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Collocazione found = getResponse.getBody();
        assertThat(found.getLocalita()).isEqualTo("Località Test");
        assertThat(found.getRegione()).isEqualTo("RegioneTest");
    }

    @Test
    void testUpdateCollocazione() {
        // Creo una Collocazione iniziale e la salvo
        Collocazione initial = new Collocazione();
        initial.setLocalita("LocOld");
        initial.setComune("ComuneOld");
        initial.setRegione("RegioneOld");
        initial.setProvincia("RO");
        collocazioneRepository.save(initial);

        // Preparo l'oggetto con i campi aggiornati
        Collocazione updated = new Collocazione();
        updated.setLocalita("LocNew");
        updated.setComune("ComuneNew");
        updated.setRegione("RegioneNew");
        updated.setProvincia("RN");

        // PUT /api/collocazione/{id}
        ResponseEntity<Collocazione> putResponse = restTemplate.exchange(
                baseUrl + "/" + initial.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(updated),
                Collocazione.class
        );
        // Se la collocazione esiste, ottengo 200 OK
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Controllo che il DB sia stato aggiornato
        Collocazione inDb = collocazioneRepository.findById(initial.getId()).orElseThrow();
        assertThat(inDb.getLocalita()).isEqualTo("LocNew");
        assertThat(inDb.getRegione()).isEqualTo("RegioneNew");
    }

    @Test
    void testDeleteCollocazione() {
        // Creo una Collocazione e la salvo
        Collocazione c = new Collocazione();
        c.setLocalita("LocDelete");
        c.setComune("ComuneDelete");
        c.setRegione("RegDelete");
        c.setProvincia("RD");
        c = collocazioneRepository.save(c);

        // DELETE /api/collocazione/{id}
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                baseUrl + "/" + c.getId(),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );
        // Se esisteva, ottengo 204 NO_CONTENT
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // Verifico che non ci sia più nel DB
        assertThat(collocazioneRepository.existsById(c.getId())).isFalse();
    }

    @Test
    void testGetAllCollocazioni() {
        // Creo due Collocazioni
        Collocazione c1 = new Collocazione();
        c1.setLocalita("Loc1");
        c1.setComune("Com1");
        c1.setRegione("Reg1");
        c1.setProvincia("R1");
        collocazioneRepository.save(c1);

        Collocazione c2 = new Collocazione();
        c2.setLocalita("Loc2");
        c2.setComune("Com2");
        c2.setRegione("Reg2");
        c2.setProvincia("R2");
        collocazioneRepository.save(c2);

        // GET /api/collocazione
        ResponseEntity<Collocazione[]> response = restTemplate.getForEntity(
                baseUrl,
                Collocazione[].class
        );

        // Se ci sono record, 200 OK
        // (Il controller ritorna 204 NO_CONTENT se la lista è vuota)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // Verifico che ci siano 2 Collocazioni
        assertThat(response.getBody()).hasSize(2);
    }
}
