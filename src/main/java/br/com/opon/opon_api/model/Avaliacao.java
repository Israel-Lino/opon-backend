package br.com.opon.opon_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDateTime dataAvaliacao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_servico", nullable = false)
    private Servico fkServico;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente fkCliente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_profissional", nullable = false)
    private Profissional fkProfissional;

}