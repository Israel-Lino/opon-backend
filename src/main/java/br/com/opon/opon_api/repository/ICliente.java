package br.com.opon.opon_api.repository;

import br.com.opon.opon_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICliente extends JpaRepository<Cliente, String> {
}
