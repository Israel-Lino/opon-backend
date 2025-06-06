package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICliente extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
}
