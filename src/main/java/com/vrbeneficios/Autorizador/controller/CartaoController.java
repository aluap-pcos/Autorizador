package com.vrbeneficios.Autorizador.controller;

import com.vrbeneficios.Autorizador.controller.dto.CartaoDTO;
import com.vrbeneficios.Autorizador.controller.dto.SaldoDTO;
import com.vrbeneficios.Autorizador.controller.form.CartaoForm;
import com.vrbeneficios.Autorizador.modelo.Cartao;
import com.vrbeneficios.Autorizador.service.CartaoService;
import com.vrbeneficios.Autorizador.utils.exception.CartaoInexistenteException;
import com.vrbeneficios.Autorizador.utils.exception.CartaoJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<SaldoDTO> buscarSaldo(@PathVariable String numeroCartao){
        try {
            BigDecimal saldo = cartaoService.getSaldo(numeroCartao);
            return ResponseEntity.ok(new SaldoDTO(saldo));
        } catch (CartaoInexistenteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartaoDTO> cadastrar(@RequestBody @Valid CartaoForm form, UriComponentsBuilder builder){
        Optional<Cartao> optionalCartao = cartaoService.buscarPorId(form.getNumeroCartao());
        if(optionalCartao.isPresent()){
            return ResponseEntity.unprocessableEntity().body(new CartaoDTO(optionalCartao.get()));
        }

        Cartao cartao = cartaoService.criar(form);
        return  ResponseEntity.status(201).body(new CartaoDTO(cartao));
    }
}
