package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IServico extends JpaRepository<Servico, Integer>, JpaSpecificationExecutor<Servico> {
}
