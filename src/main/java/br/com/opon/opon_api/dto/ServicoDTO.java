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
    private String statusServico;
    private String endereco;
    private Integer fkCliente;
    private Integer fkProfissional;
    private LocalDateTime dataSolicitada;
    private LocalDateTime dataConclusao;


}
