package com.vrbeneficios.Autorizador.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "numero_cartao", referencedColumnName = "numero")
    private Cartao numeroCartao;
    private BigDecimal valor;
    private LocalDateTime dataHora;

    public Transacao(Integer id, Cartao numeroCartao, BigDecimal valor, LocalDateTime dataHora) {
        this.id = id;
        this.numeroCartao = numeroCartao;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Transacao(Cartao numeroCartao, BigDecimal valor) {
        this.numeroCartao = numeroCartao;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }
}
