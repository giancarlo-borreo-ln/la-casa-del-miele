package com.la_casa_del_miele.microservice_apiari.model;

import jakarta.persistence.*;

@Entity
@Table(name="collocazione")
public class Collocazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="localita")
    private String localita;
    @Column(name="comune", nullable = false)
    private String comune;
    @Column(name="regione", nullable = false)
    private String regione;
    @Column(name="provincia", length = 2, nullable = false)
    private String provincia;

    public long getId() {
        return id;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
