package com.la_casa_del_miele.microservice_apiari;

import com.la_casa_del_miele.microservice_apiari.controller.MieleController;
import com.la_casa_del_miele.microservice_apiari.model.Miele;
import com.la_casa_del_miele.microservice_apiari.service.MieleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MieleControllerTest {

    private MieleService mieleService;
    private MieleController mieleController;

    @BeforeEach
    void setUp() {
        mieleService = Mockito.mock(MieleService.class);
        mieleController = new MieleController(mieleService);
    }

    @Test
    void testGetAllMieli_Success() {
        Miele miele = new Miele();
        miele.setTipo("Acacia");
        miele.setNome("Miele Acacia");

        when(mieleService.getAllMiele()).thenReturn(List.of(miele));

        ResponseEntity<List<Miele>> response = mieleController.getAllMieli();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals("Acacia", response.getBody().get(0).getTipo());
    }

    @Test
    void testGetAllMieli_NoContent() {
        when(mieleService.getAllMiele()).thenReturn(Arrays.asList());

        ResponseEntity<List<Miele>> response = mieleController.getAllMieli();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetMieleById_Success() {
        Miele miele = new Miele();
        miele.setTipo("Castagno");
        miele.setNome("Miele Castagno");

        when(mieleService.findMieleById(1L)).thenReturn(miele);

        ResponseEntity<Miele> response = mieleController.getMieleById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Castagno", response.getBody().getTipo());
    }

    @Test
    void testGetMieleById_NotFound() {
        when(mieleService.findMieleById(1L)).thenThrow(new RuntimeException());

        ResponseEntity<Miele> response = mieleController.getMieleById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateMiele() {
        Miele input = new Miele();
        input.setTipo("Tiglio");
        input.setNome("Miele Tiglio");

        Miele saved = new Miele();
        saved.setTipo("Tiglio");
        saved.setNome("Miele Tiglio");

        when(mieleService.createMiele(any(Miele.class))).thenReturn(saved);

        ResponseEntity<Miele> response = mieleController.createMiele(input);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Tiglio", response.getBody().getTipo());
    }

    @Test
    void testUpdateMiele_Success() {
        Miele updated = new Miele();
        updated.setTipo("Eucalipto");
        updated.setNome("Miele Eucalipto");

        when(mieleService.upgradeMiele(eq(1L), any(Miele.class))).thenReturn(updated);

        ResponseEntity<Miele> response = mieleController.updateMiele(1L, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eucalipto", response.getBody().getTipo());
    }

    @Test
    void testUpdateMiele_NotFound() {
        when(mieleService.upgradeMiele(eq(1L), any(Miele.class))).thenThrow(new RuntimeException());

        ResponseEntity<Miele> response = mieleController.updateMiele(1L, new Miele());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteMiele_Success() {
        doNothing().when(mieleService).deleteMieleById(1L);

        ResponseEntity<Void> response = mieleController.deleteMiele(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteMiele_NotFound() {
        doThrow(new RuntimeException()).when(mieleService).deleteMieleById(1L);

        ResponseEntity<Void> response = mieleController.deleteMiele(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
