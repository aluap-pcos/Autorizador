package com.vrbeneficios.Autorizador.controller;

import com.vrbeneficios.Autorizador.controller.form.TransacaoForm;
import com.vrbeneficios.Autorizador.service.TransacaoService;
import com.vrbeneficios.Autorizador.utils.exception.CartaoInexistenteException;
import com.vrbeneficios.Autorizador.utils.exception.SaldoInsuficienteEcxeption;
import com.vrbeneficios.Autorizador.utils.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid TransacaoForm form){
        try {
            transacaoService.novaTransacao(form);
            return ResponseEntity.status(201).body("OK");
        } catch (CartaoInexistenteException e) {
            return ResponseEntity.unprocessableEntity().body("CARTAO_INEXISTENTE");
        } catch (SenhaInvalidaException e) {
            return ResponseEntity.unprocessableEntity().body("SENHA_INVALIDA");
        } catch (SaldoInsuficienteEcxeption e) {
            return ResponseEntity.unprocessableEntity().body("SALDO_INSUFICIENTE");
        }
    }
}
