package br.com.opon.opon_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
    private int id_avaliacao;
    private BigDecimal nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;
    private int fkServico;
    private int fkCliente;
    private int fkProfissional;

}
