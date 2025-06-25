package br.com.opon.opon_api.service;


import br.com.opon.opon_api.exceptions.EmailJaCadastradoException;
import br.com.opon.opon_api.exceptions.ProfissionalNaoEncontradoException;
import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.repository.IProfissional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ProfissionalService {

    private final IProfissional repository;

    public ProfissionalService(IProfissional repository){
        this.repository = repository;
    }

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

    public Profissional editarProfissional (Profissional profissionalEditado, Integer id){
        Profissional profissionalAtual = repository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional não encontrado. Id:" + id + " /Service"));
        profissionalAtual.setNome(profissionalEditado.getNome());
        profissionalAtual.setTelefone(profissionalEditado.getTelefone());
        profissionalAtual.setEndereco(profissionalEditado.getEndereco());
        return repository.save(profissionalAtual);
    }

    public void excluirProfissional(Integer id){
        if(!repository.existsById(id)){
            throw new ProfissionalNaoEncontradoException("Profissional não encontrado. Id:" + id + " /Service");
        }
        repository.deleteById(id);
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
