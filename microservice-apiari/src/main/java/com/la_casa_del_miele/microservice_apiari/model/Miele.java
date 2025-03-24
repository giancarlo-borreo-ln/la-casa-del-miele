package com.la_casa_del_miele.microservice_apiari.model;

import jakarta.persistence.*;

@Entity
@Table(name="miele")
public class Miele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="tipo")
    private String tipo;
    @Column(name="nome")
    private String nome;

    public long getId() {
        return id;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
