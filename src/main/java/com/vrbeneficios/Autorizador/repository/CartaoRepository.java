package com.vrbeneficios.Autorizador.repository;

import com.vrbeneficios.Autorizador.modelo.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
}
