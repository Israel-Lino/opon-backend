package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.model.Avaliacao;
import br.com.opon.opon_api.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAvaliacao extends JpaRepository<Avaliacao, Integer> {
    List<Avaliacao> findByFkProfissional(Profissional profissional);
}
