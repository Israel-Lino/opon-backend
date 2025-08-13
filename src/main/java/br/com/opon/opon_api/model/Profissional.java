package br.com.opon.opon_api.model;

import br.com.opon.opon_api.model.enums.Especializacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "profissional")
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profissional", nullable = false)
    private Integer idProfissional;

    @Size(max = 100)
    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 100)
    @Email
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 100)
    @NotNull
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Size(max = 20)
    @Column(name = "telefone", length = 20)
    private String telefone;

    @Size(max = 255)
    @NotNull
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "`especialização`", nullable = false)
    private Especializacao especializacao;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "avaliacao", precision = 2, scale = 1)
    private BigDecimal avaliacao;

    @JsonIgnore
    @OneToMany(mappedBy = "fkProfissional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Servico> servicos;

    @JsonIgnore
    @OneToMany(mappedBy = "fkProfissional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "fkProfissional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos;

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
        if(this.avaliacao != null) {
            this.avaliacao = new BigDecimal("1.0");
        }
    }

}