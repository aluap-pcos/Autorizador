package com.vrbeneficios.Autorizador.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cartao")
public class Cartao {
    @Id
    private String numero;
    private String senha;
    private BigDecimal saldo;

    public Cartao(String numero, String senha, float saldo) {
        this.numero = numero;
        this.senha = senha;
        this.saldo = BigDecimal.valueOf(saldo);
    }

    public Cartao() {
    }

    public String getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = this.saldo.subtract(new BigDecimal(saldo));
    }
}
