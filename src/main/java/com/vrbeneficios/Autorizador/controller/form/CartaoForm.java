package com.vrbeneficios.Autorizador.controller.form;

import org.hibernate.validator.constraints.Length;

public class CartaoForm {
    @Length(min = 16, max = 16)
    private String numeroCartao;
    @Length(min = 4, max = 4)
    private String senha;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenha() {
        return senha;
    }
}
