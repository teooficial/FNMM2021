package com.example.fnmm2021.Classes;

import java.util.HashMap;
import java.util.Map;

public class Capitulos {

    private String id;
    private String icone;
    private String nome;
    private String numero;

    public Capitulos() {

    }

    public Capitulos(String id, String icone, String nome, String numero) {
        this.id = id;
        this.icone = icone;
        this.nome = nome;
        this.numero = numero;
    }

    public Capitulos(String icone, String nome, String numero) {
        this.icone = icone;
        this.nome = nome;
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
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

    public Map toMap() {
        HashMap result = new HashMap<>();
        result.put("nome", this.nome);
        result.put("numero", this.numero);
        result.put("icone", this.icone);
        return result;
    }
}
