package br.com.opon.opon_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "servico")
public class Servico {
    @Id
    @Column(name = "id_servico", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;

    @Size(max = 500)
    @NotNull
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @NotNull
    @Lob
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @NotNull
    @ColumnDefault("(_utf8mb4'Pedente')")
    @Lob
    @Column(name = "status_ser", nullable = false)
    private String statusSer;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_solicitada")
    private Instant dataSolicitada;

    @Column(name = "data_conclusao")
    private Instant dataConclusao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente fkCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profissional")
    private Profissional fkProfissional;

}