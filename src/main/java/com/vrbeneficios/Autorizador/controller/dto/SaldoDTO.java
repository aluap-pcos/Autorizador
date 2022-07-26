package com.vrbeneficios.Autorizador.controller.dto;

import java.math.BigDecimal;

public class SaldoDTO {
    public BigDecimal saldo;

    public SaldoDTO(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
