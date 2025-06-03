package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.entities.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfissional extends JpaRepository<Profissional, Integer> {
}
