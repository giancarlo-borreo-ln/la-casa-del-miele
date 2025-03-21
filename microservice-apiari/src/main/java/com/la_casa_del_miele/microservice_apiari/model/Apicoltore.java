package com.la_casa_del_miele.microservice_apiari.model;

import jakarta.persistence.*;

@Entity
@Table(name="apicoltore")
public class Apicoltore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="p_iva", nullable = false, unique = true)
    private String pIva;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="telefono")
    private String telefono;
    @Column(name="nome", nullable = false)
    private String nome;
    @Column(name="cognome", nullable = false)
    private String cognome;
    @Column(name="rag_sociale", nullable = false)
    private String ragioneSociale;

    public Apicoltore(Long id, String pIva, String email, String password, String telefono, String nome, String cognome, String ragioneSociale) {
        this.id = id;
        this.pIva = pIva;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.nome = nome;
        this.cognome = cognome;
        this.ragioneSociale = ragioneSociale;
    }

    public Apicoltore(){}

    public Long getId() {
        return id;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }


}
