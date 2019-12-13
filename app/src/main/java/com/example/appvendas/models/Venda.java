package com.example.appvendas.models;

import android.content.ContentValues;

import java.io.Serializable;

public class Venda implements Serializable {
    private Integer id;
    private Integer quantidade;
    private String descricao;
    private String data;
    private Float preco;
    private Integer idCliente;

    public Venda() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("quantidade", getQuantidade());
        cv.put("descricao", getDescricao());
        cv.put("data", getData());
        cv.put("preco", getPreco());
        cv.put("idCliente", getIdCliente());

        return cv;
    }
}
