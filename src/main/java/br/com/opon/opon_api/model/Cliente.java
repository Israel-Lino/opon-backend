package br.com.opon.opon_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

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

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @OneToMany(mappedBy = "fkCliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Servico> servicos;

    @JsonIgnore
    @OneToMany(mappedBy = "fkCliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "fkCliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos;

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
    }
}