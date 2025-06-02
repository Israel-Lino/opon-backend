package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAvaliacao extends JpaRepository<Avaliacao, Integer> {
}
