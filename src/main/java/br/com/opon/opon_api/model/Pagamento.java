package br.com.opon.opon_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.metamodel.model.domain.IdentifiableDomainType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Lob
    @Column(name = "metodo", nullable = false)
    private String metodoPagamento;

    @NotNull
    @ColumnDefault("(_utf8mb4'Pendente')")
    @Lob
    @Column(name = "status", nullable = false)
    private String statusPagamento;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_servico", nullable = false)
    private br.com.opon.opon_api.model.Servico fkServico;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente fkCliente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_profissional", nullable = false)
    private br.com.opon.opon_api.model.Profissional fkProfissional;

}