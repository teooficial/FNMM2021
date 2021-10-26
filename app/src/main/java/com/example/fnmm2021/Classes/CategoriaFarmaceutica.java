package com.example.fnmm2021.Classes;

public class CategoriaFarmaceutica {

    private String id;
    private String categoria;

    public CategoriaFarmaceutica() {
    }

    public CategoriaFarmaceutica(String id, String nome) {
        this.id = id;
        this.categoria = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
