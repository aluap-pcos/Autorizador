package com.vrbeneficios.Autorizador.service;

import com.vrbeneficios.Autorizador.repository.CartaoRepository;
import com.vrbeneficios.Autorizador.controller.form.CartaoForm;
import com.vrbeneficios.Autorizador.modelo.Cartao;
import com.vrbeneficios.Autorizador.utils.exception.CartaoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartaoService {
    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao criar(CartaoForm form){
        Cartao cartao = new Cartao(form.getNumeroCartao(), form.getSenha(), 500.00F);
        return cartaoRepository.save(cartao);
    }

    public Optional<Cartao> buscarPorId(String cartaoId){
        return cartaoRepository.findById(cartaoId);
    }

    public BigDecimal getSaldo(String numeroCartao) throws CartaoInexistenteException {
        Optional<Cartao> optionalCartao = cartaoRepository.findById(numeroCartao);
        if(optionalCartao.isEmpty()){
            throw new CartaoInexistenteException();
        }

        return optionalCartao.get().getSaldo();
    }
}
