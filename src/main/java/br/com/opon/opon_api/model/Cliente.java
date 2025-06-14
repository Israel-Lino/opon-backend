package br.com.opon.opon_api.model;

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
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = {"email"})
})
public class Cliente {
    @Id
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 100)
    @NotNull
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Size(max = 20)
    @Column(name = "telefene", length = 20)
    private String telefene;

    @Size(max = 255)
    @NotNull
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_cadastro")
    private Instant dataCadastro;

}