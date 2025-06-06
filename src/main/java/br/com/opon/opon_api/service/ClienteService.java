package br.com.opon.opon_api.service;

import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.repository.ICliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ICliente repository;
    //Adicionar BCrypt

    public ClienteService(ICliente repository) {
        this.repository = repository;
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente criarCliente(Cliente cliente) {
        cliente.setDataCadastro(Instant.now());
        return repository.save(cliente);
    }

    public Cliente editarCliente(Cliente cliente) {
        Cliente novoCliente = buscarPorEmail(cliente.getEmail());
        novoCliente.setIdCliente(cliente.getIdCliente());
        novoCliente.setDataCadastro(Instant.now());
        return repository.save(novoCliente);
    }

    public Boolean excluirCliente(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public Boolean validarCliente(String email, String senha) {
        //Terminar validação após adicionar BCrypt
        Optional<Cliente> clienteOptional = repository.findByEmail(email);
        if (clienteOptional.isPresent()) {
            if (senha.equals(clienteOptional.get().getSenha())) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarPorEmail(String email) {
        return repository.findByEmail(email).orElse(new Cliente());
    }

    public Cliente buscarPorId(Integer id) {
        return repository.findById(id).orElse(new Cliente());
    }

}
