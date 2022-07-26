package com.vrbeneficios.Autorizador.service;

import com.vrbeneficios.Autorizador.controller.form.TransacaoForm;
import com.vrbeneficios.Autorizador.modelo.Cartao;
import com.vrbeneficios.Autorizador.repository.CartaoRepository;
import com.vrbeneficios.Autorizador.repository.TransacaoRepository;
import com.vrbeneficios.Autorizador.utils.exception.CartaoInexistenteException;
import com.vrbeneficios.Autorizador.utils.exception.SaldoInsuficienteEcxeption;
import com.vrbeneficios.Autorizador.utils.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransacaoService {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public void novaTransacao(TransacaoForm form) throws CartaoInexistenteException, SenhaInvalidaException, SaldoInsuficienteEcxeption {
        Optional<Cartao> optionalCartao = cartaoRepository.findById(form.getNumeroCartao());
        if(optionalCartao.isEmpty()){
            throw new CartaoInexistenteException();
        }
        if(!optionalCartao.get().getSenha().equals(form.getSenhaCartao())){
            throw new SenhaInvalidaException();
        }
        if(optionalCartao.get().getSaldo().compareTo(new BigDecimal(form.getValor())) < 0){
            throw new SaldoInsuficienteEcxeption();
        }

        optionalCartao.get().setSaldo(form.getValor());
        transacaoRepository.save(form.converter(optionalCartao.get()));
        cartaoRepository.save(optionalCartao.get());
    }
}
