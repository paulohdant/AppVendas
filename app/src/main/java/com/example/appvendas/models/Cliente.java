package com.example.appvendas.models;

import java.io.Serializable;

public class Cliente implements Serializable {

    private Integer id;
    private String nome;
    private String numero;

    public Cliente(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public Cliente() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString(){
        return nome;
    }
}
