package br.com.opon.opon_api.service;

import br.com.opon.opon_api.dto.ServicoDTO;
import br.com.opon.opon_api.exceptions.ClienteNaoEncontradoException;
import br.com.opon.opon_api.exceptions.ServicoNaoEncontrado;
import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.model.Servico;
import br.com.opon.opon_api.repository.IServico;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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
        Cliente cliente = clienteService.buscarCliente(servicoDto.getFk_cliente());

        servico.setTitulo(servicoDto.getTitulo());
        servico.setDescricao(servicoDto.getDescricao());
        servico.setCategoria(servicoDto.getCategoria());
        servico.setStatusServico("Pendente");
        servico.setDataSolicitada(dataAtual());
        //Todo adicionar método para dataConclusão
        servico.setFkCliente(cliente);
        if (servicoDto.getFk_profissional() != 0) {
            Profissional profissional = profissionalService.buscarProfissional(servicoDto.getFk_profissional());
            servico.setFkProfissional(profissional);
        }
        return repository.save(servico);
    }

    public Servico editarServico(ServicoDTO servicoDtoEditado, Integer id) {
        Servico servicoAtual = repository.findById(id).orElseThrow(() -> new ServicoNaoEncontrado("Serviço não encontrado. Id:" + id + " /Service"));
        servicoAtual.setTitulo(servicoDtoEditado.getTitulo());
        servicoAtual.setDescricao(servicoDtoEditado.getDescricao());
        servicoAtual.setCategoria(servicoDtoEditado.getCategoria());
        //Todo aqui ficará o método para alterar o Status
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
