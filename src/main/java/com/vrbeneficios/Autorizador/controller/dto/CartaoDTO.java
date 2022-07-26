package com.vrbeneficios.Autorizador.controller.dto;

import com.vrbeneficios.Autorizador.modelo.Cartao;

public class CartaoDTO {
    private String senha;
    private String numeroCartao;

    public CartaoDTO(Cartao cartao) {
        this.senha = cartao.getSenha();
        this.numeroCartao = cartao.getNumero();
    }

    public String getSenha() {
        return senha;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
