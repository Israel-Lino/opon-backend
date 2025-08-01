package br.com.opon.opon_api.service;

import br.com.opon.opon_api.exceptions.ClienteNaoEncontradoException;
import br.com.opon.opon_api.exceptions.EmailJaCadastradoException;
import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.repository.ICliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class ClienteService {


    private final ICliente repository;
    //Todo Adicionar BCrypt

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente buscarCliente(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado. Id:" + id + " /Service"));
    }

    public Cliente criarCliente(Cliente cliente) {
        validarEmailCliente(cliente.getEmail());
        cliente.setDataCadastro(dataAtual());
        return repository.save(cliente);
    }

    public Cliente editarCliente(Cliente clienteEditado, Integer id) {
        Cliente clienteAtual = repository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado. Id:" + id + " /Service"));
        clienteAtual.setNome(clienteEditado.getNome());
        clienteAtual.setTelefone(clienteEditado.getTelefone());
        clienteAtual.setEndereco(clienteEditado.getEndereco());
        return repository.save(clienteAtual);
    }

    public Boolean excluirCliente(Integer id) {
        if(!repository.existsById(id)){
            throw new ClienteNaoEncontradoException("Cliente não encontrado. Id:" + id + " /Service");
        }
        repository.deleteById(id);
        return true;
    }

    public void validarEmailCliente(String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailJaCadastradoException("E-mail já cadastrado");
        }
    }

    public LocalDateTime dataAtual(){
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

}
