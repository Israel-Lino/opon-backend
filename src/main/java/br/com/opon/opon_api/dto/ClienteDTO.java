package br.com.opon.opon_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Integer idCliente;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;
    private LocalDateTime dataCadastro;

}
