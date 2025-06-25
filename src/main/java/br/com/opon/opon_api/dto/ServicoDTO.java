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
    private int fk_cliente;
    private int fk_profissional;
    private LocalDateTime dataSolicitada;
    private LocalDateTime dataConclusao;


}
