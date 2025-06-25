package br.com.opon.opon_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicoNaoEncontrado extends RuntimeException {
    public ServicoNaoEncontrado(String message) {
        super(message);
    }
}
