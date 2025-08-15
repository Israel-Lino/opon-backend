package br.com.opon.opon_api.service;

import br.com.opon.opon_api.dto.ServicoDTO;
import br.com.opon.opon_api.exceptions.ClienteNaoEncontradoException;
import br.com.opon.opon_api.exceptions.ServicoNaoEncontrado;
import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.model.Servico;
import br.com.opon.opon_api.model.enums.CategoriaServico;
import br.com.opon.opon_api.model.enums.StatusServico;
import br.com.opon.opon_api.repository.IServico;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import static br.com.opon.opon_api.repository.specification.ServicoSpecification.*;

@Service
public class ServicoService {

    private final IServico repository;
    private final ClienteService clienteService;
    private final ProfissionalService profissionalService;

    public ServicoService(IServico repository, ClienteService clienteService, ProfissionalService profissionalService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.profissionalService = profissionalService;
    }

    public List<Servico> listarServicos() {
        return repository.findAll();
    }

    public Servico buscarServico(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ServicoNaoEncontrado("Serviço não encontrado. Id:" + id + " /Service"));
    }

    public Servico criarServico(ServicoDTO servicoDto) {
        Servico servico = new Servico();
        Cliente cliente = clienteService.buscarCliente(servicoDto.getFkCliente());

        servico.setTitulo(servicoDto.getTitulo());
        servico.setDescricao(servicoDto.getDescricao());
        CategoriaServico categoria = CategoriaServico.valueOf(servicoDto.getCategoria());
        //Todo Concertar status para receber o valor do DTO
        servico.setCategoria(categoria);
        servico.setStatusServico(StatusServico.Aceito);
        servico.setDataSolicitada(dataAtual());
        //Todo adicionar método para dataConclusão
        servico.setFkCliente(cliente);
        if (servicoDto.getFkProfissional() != 0) {
            Profissional profissional = profissionalService.buscarProfissional(servicoDto.getFkProfissional());
            servico.setFkProfissional(profissional);
        }
        return repository.save(servico);
    }

    public List<Servico> buscarServicoFiltrados(StatusServico status, CategoriaServico categoria, Integer idCliente) {
        Specification<Servico> spec = Specification.where(null);
        if (status != null) spec = spec.and(porStatus(status));
        if (categoria != null) spec = spec.and(porCategoria(categoria));
        if (idCliente != null) spec = spec.and(porClienteId(idCliente));
        return repository.findAll(spec);
    }

    public Servico editarServico(ServicoDTO servicoDtoEditado, Integer id) {
        Servico servicoAtual = repository.findById(id).orElseThrow(() -> new ServicoNaoEncontrado("Serviço não encontrado. Id:" + id + " /Service"));
        servicoAtual.setTitulo(servicoDtoEditado.getTitulo());
        servicoAtual.setDescricao(servicoDtoEditado.getDescricao());
        CategoriaServico categoria = CategoriaServico.valueOf(servicoDtoEditado.getCategoria());
        servicoAtual.setCategoria(categoria);
        //Todo aqui ficará o método para alterar o Status
        //Todo adicionar metodo para adicionar o profissional
        return repository.save(servicoAtual);
    }

    public Boolean excluirServico(Integer id) {
        if (!repository.existsById(id)) {
            throw new ServicoNaoEncontrado("Serviço não encontrado. Id:" + id + " /Service");
        }
        repository.deleteById(id);
        return true;
    }

    //Todo adicionar método para alterarStatus

    //Todo adicionar método para filtar os serviços pela categoria

    public LocalDateTime dataAtual() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
