package br.com.opon.opon_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @Column(name = "id_avaliacao", nullable = false)
    private Integer id_avaliacao;

    @ColumnDefault("1.0")
    @Column(name = "nota", precision = 2, scale = 1)
    private BigDecimal nota;

    @Size(max = 500)
    @Column(name = "comentario", length = 500)
    private String comentario;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_avaliacao")
    private Instant dataAvaliacao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_servico", nullable = false)
    private br.com.opon.opon_api.model.Servico fkServico;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private br.com.opon.opon_api.model.Cliente fkCliente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_profissional", nullable = false)
    private br.com.opon.opon_api.model.Profissional fkProfissional;

}