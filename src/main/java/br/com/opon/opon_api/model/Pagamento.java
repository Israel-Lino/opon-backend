package br.com.opon.opon_api.model;

import br.com.opon.opon_api.model.enums.MetodoPagamento;
import br.com.opon.opon_api.model.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento", nullable = false)
    private Integer idPagamento;

    @NotNull
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo", nullable = false)
    private MetodoPagamento metodoPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPagamento statusPagamento;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

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

    @PrePersist
    protected void onCreate() {
        if(this.statusPagamento == null) {
            this.statusPagamento = StatusPagamento.Pendente;
        }
    }
}