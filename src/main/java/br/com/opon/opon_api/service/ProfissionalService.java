package br.com.opon.opon_api.service;


import br.com.opon.opon_api.exceptions.EmailJaCadastradoException;
import br.com.opon.opon_api.exceptions.ProfissionalNaoEncontradoException;
import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.repository.IAvaliacao;
import br.com.opon.opon_api.repository.IProfissional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfissionalService {

    private final IProfissional repository;

    public List<Profissional> listarProfissionais(){
        return repository.findAll();
    }

    public Profissional buscarProfissional(Integer id){
        return repository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional não encontrado. Id:" + id + " /Service"));
    }

    public Profissional criarProfissional (Profissional profissional){
        validarEmailProfissional(profissional.getEmail());
        profissional.setDataCadastro(dataAtual());
        profissional.setAvaliacao(new BigDecimal("1.0"));
        return repository.save(profissional);
    }

    public Profissional validarProfissional(Profissional profissional){
        return repository.findByEmail(profissional.getEmail()).orElseThrow(() -> new RuntimeException("Usuário não encontrado no banco"));
    }

    public Profissional editarProfissional (Profissional profissionalEditado, Integer id){
        Profissional profissionalAtual = repository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional não encontrado. Id:" + id + " /Service"));
        profissionalAtual.setNome(profissionalEditado.getNome());
        profissionalAtual.setTelefone(profissionalEditado.getTelefone());
        profissionalAtual.setEndereco(profissionalEditado.getEndereco());
        profissionalAtual.setEspecializacao(profissionalEditado.getEspecializacao());
        return repository.save(profissionalAtual);
    }

    public Boolean excluirProfissional(Integer id){
        if(!repository.existsById(id)){
            throw new ProfissionalNaoEncontradoException("Profissional não encontrado. Id:" + id + " /Service");
        }
        repository.deleteById(id);
        return true;
    }

    //Todo adicionar método para aumentar a avaliação do profissional

    //Todo adicionar método para alterar ou adicinoar uma especialização do profissional

    public void validarEmailProfissional(String email){
        if(repository.existsByEmail(email)){
            throw new EmailJaCadastradoException("E-mail já cadastrado");
        }
    }

    public LocalDateTime dataAtual(){
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

}
