package com.vrbeneficios.Autorizador.controller.form;

import com.vrbeneficios.Autorizador.modelo.Cartao;
import com.vrbeneficios.Autorizador.modelo.Transacao;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class TransacaoForm {
    @Length(min = 16, max = 16)
    private String numeroCartao;
    @Length(min = 4, max = 4)
    private String senhaCartao;
    private Float valor;

    public TransacaoForm(String numeroCartao, String senhaCartao, Float valor) {
        this.numeroCartao = numeroCartao;
        this.senhaCartao = senhaCartao;
        this.valor = valor;
    }

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
