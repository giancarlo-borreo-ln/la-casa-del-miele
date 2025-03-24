package com.la_casa_del_miele.microservice_apiari;

import com.la_casa_del_miele.microservice_apiari.controller.ApiarioController;
import com.la_casa_del_miele.microservice_apiari.model.Apiario;
import com.la_casa_del_miele.microservice_apiari.service.ApiarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.HttpStatus.*;

class ApiarioControllerTest {

    private ApiarioService mockService;
    private ApiarioController controller;

    @BeforeEach
    void setUp() {
        // Creiamo il mock della service
        mockService = Mockito.mock(ApiarioService.class);

        // Passiamo il mock al controller (costruttore)
        controller = new ApiarioController(mockService);
    }

    @Test
    void testGetAllApiari_Empty() {
        // Mock: lista vuota
        Mockito.when(mockService.getAllApiari()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Apiario>> response = controller.getAllApiari();
        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void testGetAllApiari_NonEmpty() {
        Apiario a = new Apiario();
        a.setNumArnie(5);
        a.setQta(100);
        a.setAnno(2023);

        Mockito.when(mockService.getAllApiari()).thenReturn(List.of(a));

        ResponseEntity<List<Apiario>> response = controller.getAllApiari();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getQta()).isEqualTo(100);
    }

    @Test
    void testGetApiarioById_Success() {
        // Mock: service torna un Apiario
        Apiario a = new Apiario();
        a.setNumArnie(10);
        a.setQta(50);
        a.setAnno(2024);

        Mockito.when(mockService.getApiarioById(1L)).thenReturn(a);

        ResponseEntity<Apiario> response = controller.getApiarioById(1L);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getQta()).isEqualTo(50);
    }

    @Test
    void testGetApiarioById_NotFound() {
        // Mock: service lancia eccezione
        Mockito.when(mockService.getApiarioById(99L)).thenThrow(new RuntimeException("Not Found"));

        ResponseEntity<Apiario> response = controller.getApiarioById(99L);
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void testCreateApiario() {
        // Mock: la service crea e torna un Apiario
        Apiario newA = new Apiario();
        newA.setNumArnie(15);
        newA.setQta(70);
        newA.setAnno(2025);

        Apiario created = new Apiario();
        created.setNumArnie(15);
        created.setQta(70);
        created.setAnno(2025);

        Mockito.when(mockService.createApiario(any(Apiario.class))).thenReturn(created);

        ResponseEntity<Apiario> response = controller.createApiario(newA);
        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        assertThat(response.getBody().getQta()).isEqualTo(70);
    }

    @Test
    void testUpdateApiario_Success() {
        Apiario updated = new Apiario();
        updated.setNumArnie(20);
        updated.setQta(100);
        updated.setAnno(2022);

        Mockito.when(mockService.updateApiario(eq(1L), any(Apiario.class))).thenReturn(updated);

        ResponseEntity<Apiario> response = controller.updateApiario(1L, new Apiario());
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getAnno()).isEqualTo(2022);
    }

    @Test
    void testUpdateApiario_NotFound() {
        Mockito.when(mockService.updateApiario(eq(99L), any(Apiario.class)))
                .thenThrow(new RuntimeException("Not Found"));

        ResponseEntity<Apiario> response = controller.updateApiario(99L, new Apiario());
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void testDeleteApiario_Success() {
        // Mock: non fa nulla, va a buon fine
        Mockito.doNothing().when(mockService).deleteApiarioById(1L);

        ResponseEntity<Void> response = controller.deleteApiario(1L);
        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    void testDeleteApiario_NotFound() {
        Mockito.doThrow(new RuntimeException("Not Found")).when(mockService).deleteApiarioById(99L);

        ResponseEntity<Void> response = controller.deleteApiario(99L);
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }
}
