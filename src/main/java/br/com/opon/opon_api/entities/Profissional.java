package br.com.opon.opon_api.entities;

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
@Table(name = "profissional", uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = {"email"})
})
public class Profissional {
    @Id
    @Column(name = "id_profissional", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "senha", nullable = false)
    private String senha;

    @Size(max = 20)
    @Column(name = "telefene", length = 20)
    private String telefene;

    @Size(max = 255)
    @NotNull
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @NotNull
    @Lob
    @Column(name = "`especialização`", nullable = false)
    private String especializacao;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_cadastro")
    private Instant dataCadastro;

    @ColumnDefault("1.0")
    @Column(name = "avaliacao", precision = 2, scale = 1)
    private BigDecimal avaliacao;

}