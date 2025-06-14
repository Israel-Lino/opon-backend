package br.com.opon.opon_api.service;

import br.com.opon.opon_api.exceptions.EmailJaCadastrado;
import br.com.opon.opon_api.exceptions.ProfissionalNaoEncontrado;
import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.repository.IProfissional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ProfissionalService {

    private final IProfissional repository;


    public ProfissionalService(IProfissional repository) {
        this.repository = repository;
    }

    public List<Profissional> listarProfissionais() {
        return repository.findAll();
    }

    public Profissional buscarProfissionalID(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontrado("Profissional não encontrado. ID: " + id + " /Service"));
    }

    public Profissional criarProfissional (Profissional profissional) {
        validarEmail(profissional.getEmail());
        profissional.setDataCadastro(dataAtual());
        return repository.save(profissional);
    }

    public Profissional editarProfissional(Integer id, Profissional profissional) {
        Profissional profissionalAtual = repository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontrado("Profissional não encontrado. ID: " + id + " /Service"));;
        profissionalAtual.setNome(profissional.getNome());
        profissionalAtual.setEndereco(profissional.getEndereco());
        profissionalAtual.setTelefene(profissional.getTelefene());
        return repository.save(profissionalAtual);
    }

    public void deletarProfissional(Integer id) {
        if (!repository.existsById(id)) {
            throw new ProfissionalNaoEncontrado("Profissional não encontrado. ID: \" + id + \" /Service");
        }
        repository.deleteById(id);
    }

    public void validarEmail(String email){
        if(repository.existsByEmail(email)){
            throw new EmailJaCadastrado("Email já está cadastrado.");
        }
    }

    public LocalDateTime dataAtual(){
        return LocalDateTime.now((ZoneId.of("America/Sao_Paulo")));
    }
}
