package com.vrbeneficios.Autorizador.controller.form;

import com.vrbeneficios.Autorizador.modelo.Cartao;
import com.vrbeneficios.Autorizador.modelo.Transacao;

import java.math.BigDecimal;

public class TransacaoForm {
    private String numeroCartao;
    private String senhaCartao;
    private Float valor;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenhaCartao() {
        return senhaCartao;
    }

    public Float getValor() {
        return valor;
    }

    public Transacao converter(Cartao cartao){
        return new Transacao(cartao, new BigDecimal(valor));
    }
}
