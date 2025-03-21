package com.la_casa_del_miele.microservice_apiari.model;

import jakarta.persistence.*;

@Entity
@Table(name = "apiario")
public class Apiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_arnie")
    private Integer numArnie;

    @ManyToOne
    @JoinColumn(name = "id_apicoltore", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_apiario_apicoltore"))
    private Apicoltore apicoltore;

    @Column(name = "qta", nullable = false)
    private Integer qta;

    @Column(name = "anno", nullable = false)
    private Integer anno;

    @ManyToOne
    @JoinColumn(name = "id_collocazione", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_apiario_collocazione"))
    private Collocazione collocazione;

    @ManyToOne
    @JoinColumn(name = "id_miele", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_apiario_miele"))
    private Miele miele;

    public Apiario() {}

    public Apiario(Long id, Integer numArnie, Apicoltore apicoltore, Integer qta, Integer anno, Collocazione collocazione, Miele miele) {
        this.id = id;
        this.numArnie = numArnie;
        this.apicoltore = apicoltore;
        this.qta = qta;
        this.anno = anno;
        this.collocazione = collocazione;
        this.miele = miele;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumArnie() {
        return numArnie;
    }

    public void setNumArnie(Integer numArnie) {
        this.numArnie = numArnie;
    }

    public Apicoltore getApicoltore() {
        return apicoltore;
    }

    public void setApicoltore(Apicoltore apicoltore) {
        this.apicoltore = apicoltore;
    }

    public Integer getQta() {
        return qta;
    }

    public void setQta(Integer qta) {
        this.qta = qta;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Collocazione getCollocazione() {
        return collocazione;
    }

    public void setCollocazione(Collocazione collocazione) {
        this.collocazione = collocazione;
    }

    public Miele getMiele() {
        return miele;
    }

    public void setMiele(Miele miele) {
        this.miele = miele;
    }
}
