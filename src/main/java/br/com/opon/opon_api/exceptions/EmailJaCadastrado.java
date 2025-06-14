package br.com.opon.opon_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailJaCadastrado extends RuntimeException {
    public EmailJaCadastrado(String mensagem) {
        super(mensagem);
    }
}
