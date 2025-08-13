package br.com.opon.opon_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalDTO {

    private Integer idProfissional;
    private String nome;
    private String telefone;
    private String especializacao;
    private BigDecimal avaliacao;

}
