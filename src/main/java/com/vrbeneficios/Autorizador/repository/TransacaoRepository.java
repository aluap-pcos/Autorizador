package com.vrbeneficios.Autorizador.repository;

import com.vrbeneficios.Autorizador.modelo.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
}
