package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProfissional extends JpaRepository<Profissional, Integer> {
    Boolean existsByEmail(String email);
    Optional<Profissional> findByEmail(String email);
}
