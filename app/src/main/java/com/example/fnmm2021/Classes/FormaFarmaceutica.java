package com.example.fnmm2021.Classes;

import java.util.HashMap;
import java.util.Map;

public class FormaFarmaceutica {
    private String id;
    private String nome;
    private String numero;

    public FormaFarmaceutica() {
    }

    public FormaFarmaceutica(String id, String nome, String numero) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
    }

    public FormaFarmaceutica(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    private Map toMap() {
        HashMap result = new HashMap<>();

        result.put("nome", this.nome);
        result.put("numero", this.numero);

        return result;
    }
}
