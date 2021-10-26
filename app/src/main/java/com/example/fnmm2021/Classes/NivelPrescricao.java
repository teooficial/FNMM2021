package com.example.fnmm2021.Classes;

import java.util.HashMap;
import java.util.Map;

public class NivelPrescricao {

    private String id;
    private String nome;
    private String nivel;

    public NivelPrescricao() {
    }

    public NivelPrescricao(String id, String nome, String nivel) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
    }

    public NivelPrescricao(String nome, String nivel) {
        this.nome = nome;
        this.nivel = nivel;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Map toMap() {

        HashMap result = new HashMap<>();
        result.put("nome", this.nome);
        result.put("nivel", this.nivel);

        return result;
    }
}
