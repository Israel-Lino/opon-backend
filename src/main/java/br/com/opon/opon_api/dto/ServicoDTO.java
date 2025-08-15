package br.com.opon.opon_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {

    private String titulo;
    private String descricao;
    private String categoria;
    private String status;
    private String endereco;
    private int fkCliente;
    private int fkProfissional;
    private LocalDateTime dataSolicitada;
    private LocalDateTime dataConclusao;


}
