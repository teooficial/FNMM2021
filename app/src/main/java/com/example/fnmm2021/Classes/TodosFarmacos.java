package com.example.fnmm2021.Classes;

import java.util.HashMap;
import java.util.Map;

public class TodosFarmacos {

    private String id;
    private String nome;
    private String apresentacao;
    private String viaadministracao;
    private String dosagem;
    private String indicacoes;
    private String efeitossecundarios;
    private String contraindicacoes;
    private String notasprecaucoes;
    private String nivelprescricao;

    public TodosFarmacos() {
    }

    public TodosFarmacos(String nome, String apresentacao, String viaadministracao, String dosagem, String indicacoes, String efeitossecundarios, String contraindicacoes, String notasprecaucoes, String nivelprescricao) {
        this.nome = nome;
        this.apresentacao = apresentacao;
        this.viaadministracao = viaadministracao;
        this.dosagem = dosagem;
        this.indicacoes=indicacoes;
        this.efeitossecundarios = efeitossecundarios;
        this.contraindicacoes = contraindicacoes;
        this.notasprecaucoes = notasprecaucoes;
        this.nivelprescricao = nivelprescricao;
    }

    public TodosFarmacos(String id, String nome, String apresentacao, String viaadministracao, String dosagem,String indicacoes, String efeitossecundarios, String contraindicacoes, String notasprecaucoes, String nivelprescricao) {
        this.id = id;
        this.nome = nome;
        this.apresentacao = apresentacao;
        this.viaadministracao = viaadministracao;
        this.dosagem = dosagem;
        this.indicacoes=indicacoes;
        this.efeitossecundarios = efeitossecundarios;
        this.contraindicacoes = contraindicacoes;
        this.notasprecaucoes = notasprecaucoes;
        this.nivelprescricao = nivelprescricao;
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

    public String getApresentacao() {
        return apresentacao;
    }

    public String getViaadministracao() {
        return viaadministracao;
    }

    public void setViaadministracao(String viaadministracao) {
        this.viaadministracao = viaadministracao;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getIndicacoes() {
        return indicacoes;
    }

    public void setIndicacoes(String indicacoes) {
        this.indicacoes = indicacoes;
    }

    public String getEfeitossecundarios() {
        return efeitossecundarios;
    }

    public void setEfeitossecundarios(String efeitossecundarios) {
        this.efeitossecundarios = efeitossecundarios;
    }

    public String getContraindicacoes() {
        return contraindicacoes;
    }

    public void setContraindicacoes(String contraindicacoes) {
        this.contraindicacoes = contraindicacoes;
    }

    public String getNotasprecaucoes() {
        return notasprecaucoes;
    }

    public void setNotasprecaucoes(String notasprecaucoes) {
        this.notasprecaucoes = notasprecaucoes;
    }

    public String getNivelprescricao() {
        return nivelprescricao;
    }

    public void setNivelprescricao(String nivelprescricao) {
        this.nivelprescricao = nivelprescricao;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public Map toMap() {

        HashMap result = new HashMap<>();
        result.put("nome", this.nome);
        result.put("apresentacao", this.apresentacao);
        result.put("viaadministracao", this.viaadministracao);
        result.put("indicacoes", this.indicacoes);
        result.put("dosagem", this.dosagem);
        result.put("efeitossecundarios", this.efeitossecundarios);
        result.put("contraindicacao", this.contraindicacoes);
        result.put("notasprecaucoes", this.notasprecaucoes);
        result.put("nivelprescricao", this.nivelprescricao);

        return result;
    }
}
