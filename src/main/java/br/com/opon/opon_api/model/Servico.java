package br.com.opon.opon_api.model;

import br.com.opon.opon_api.model.enums.CategoriaServico;
import br.com.opon.opon_api.model.enums.StatusServico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico", nullable = false)
    private Integer idServico;

    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;

    @Size(max = 500)
    @NotNull
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaServico categoria;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusServico statusServico;

    @Column(name = "data_solicitada")
    private LocalDateTime dataSolicitada;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente fkCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profissional")
    private Profissional fkProfissional;

    @JsonIgnore
    @OneToMany(mappedBy = "fkServico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "fkServico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos;

    @PrePersist
    protected void onCreate() {
        this.dataSolicitada = LocalDateTime.now();
        if (this.statusServico == null){
            this.statusServico = StatusServico.Pendente;
        }
    }
}