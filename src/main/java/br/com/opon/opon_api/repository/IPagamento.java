package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagamento extends JpaRepository<Pagamento, Integer> {
}
