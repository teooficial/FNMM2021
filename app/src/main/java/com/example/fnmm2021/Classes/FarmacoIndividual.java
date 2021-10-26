package com.example.fnmm2021.Classes;

public class FarmacoIndividual {

    private String id;
    private String nome;
    private String apresentacao;
    private String indicacoes;
    private String capitulos;
    private String contraindicacoes;
    private String dosagem;
    private String efeitossecundarios;
    private String formafarmaceutica;
    private String nivelprescricao;
    private String notasprecaucoes;
    private String viaadministracao;

    public FarmacoIndividual() {
    }

    public FarmacoIndividual(String id, String nome, String apresentacao, String indicacoes, String capitulos, String contraindicacoes, String dosagem, String efeitossecundarios, String formafarmaceutica, String nivelprescricao, String notasprecaucoes, String viaadministracao) {
        this.id = id;
        this.nome = nome;
        this.apresentacao = apresentacao;
        this.indicacoes = indicacoes;
        this.capitulos = capitulos;
        this.contraindicacoes = contraindicacoes;
        this.dosagem = dosagem;
        this.efeitossecundarios = efeitossecundarios;
        this.formafarmaceutica = formafarmaceutica;
        this.nivelprescricao = nivelprescricao;
        this.notasprecaucoes = notasprecaucoes;
        this.viaadministracao = viaadministracao;
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

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public String getIndicacoes() {
        return indicacoes;
    }

    public void setIndicacoes(String indicacoes) {
        this.indicacoes = indicacoes;
    }

    public String getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(String capitulos) {
        this.capitulos = capitulos;
    }

    public String getContraindicacoes() {
        return contraindicacoes;
    }

    public void setContraindicacoes(String contraindicacoes) {
        this.contraindicacoes = contraindicacoes;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getEfeitossecundarios() {
        return efeitossecundarios;
    }

    public void setEfeitossecundarios(String efeitossecundarios) {
        this.efeitossecundarios = efeitossecundarios;
    }

    public String getFormafarmaceutica() {
        return formafarmaceutica;
    }

    public void setFormafarmaceutica(String formafarmaceutica) {
        this.formafarmaceutica = formafarmaceutica;
    }

    public String getNivelprescricao() {
        return nivelprescricao;
    }

    public void setNivelprescricao(String nivelprescricao) {
        this.nivelprescricao = nivelprescricao;
    }

    public String getNotasprecaucoes() {
        return notasprecaucoes;
    }

    public void setNotasprecaucoes(String notasprecaucoes) {
        this.notasprecaucoes = notasprecaucoes;
    }

    public String getViaadministracao() {
        return viaadministracao;
    }

    public void setViaadministracao(String viaadministracao) {
        this.viaadministracao = viaadministracao;
    }
}
