package br.com.opon.opon_api.service;

import br.com.opon.opon_api.dto.AvaliacaoDTO;
import br.com.opon.opon_api.dto.ProfissionalDTO;
import br.com.opon.opon_api.exceptions.AvaliacaoNaoEncontrada;
import br.com.opon.opon_api.model.Avaliacao;
import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.model.Servico;
import br.com.opon.opon_api.repository.IAvaliacao;
import br.com.opon.opon_api.repository.IProfissional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class AvaliacaoService {

    private final IAvaliacao repository;
    private final IProfissional repositoryProfissional;
    private final ServicoService servicoService;
    private final ClienteService clienteService;
    private final ProfissionalService profissionalService;

    public List<Avaliacao> listarAvaliacoes() {
        return repository.findAll();
    }

    public Avaliacao buscarAvaliacao(Integer id) {
        return repository.findById(id).orElseThrow(() -> new AvaliacaoNaoEncontrada("Avaliação não encontrada. Id:" + id + " /Service"));
    }

    public Avaliacao criarAvaliacao(AvaliacaoDTO avaliacaoDto) {
        Avaliacao avaliacao = new Avaliacao();
        Servico servico = servicoService.buscarServico(avaliacaoDto.getFkServico());
        Cliente cliente = clienteService.buscarCliente(servico.getFkCliente().getIdCliente());
        Profissional profissional = profissionalService.buscarProfissional(servico.getFkProfissional().getIdProfissional());

        //Nota não pode ser 0 ou > 5
        avaliacao.setNota(avaliacaoDto.getNota());
        atualizarNotaProfissional(profissional.getIdProfissional(), avaliacaoDto);

        avaliacao.setComentario(avaliacaoDto.getComentario());
        avaliacao.setDataAvaliacao(dataAtual());
        avaliacao.setFkServico(servico);
        avaliacao.setFkCliente(cliente);
        avaliacao.setFkProfissional(profissional);

        return repository.save(avaliacao);
    }

    public Avaliacao editarAvaliacao(AvaliacaoDTO avaliacaoDtoEditado, Integer id) {
        Avaliacao avaliacaoAtual = repository.findById(id).orElseThrow(() -> new AvaliacaoNaoEncontrada("Avaliação não encontrada. Id:" + id + " /Service"));
        Profissional profissional = profissionalService.buscarProfissional(avaliacaoAtual.getFkProfissional().getIdProfissional());
        avaliacaoAtual.setNota(avaliacaoDtoEditado.getNota());
        atualizarNotaProfissional(profissional.getIdProfissional(), avaliacaoDtoEditado);
        avaliacaoAtual.setComentario(avaliacaoDtoEditado.getComentario());
        return repository.save(avaliacaoAtual);
    }

    public Boolean deletarAvaliacao(Integer id) {
        if (!repository.existsById(id)) {
            throw new AvaliacaoNaoEncontrada("Avaliação não encontrada. Id:\" + id + \" /Service");
        }
        repository.deleteById(id);
        return true;
    }

    public void atualizarNotaProfissional(Integer id, AvaliacaoDTO avaliacaoDto) {
        Profissional profissional = profissionalService.buscarProfissional(id);
        List<Avaliacao> todasAvaliacoesProfissional = repository.findByFkProfissional(profissional);

        double media = todasAvaliacoesProfissional.stream()
                .mapToDouble(a -> a.getNota().doubleValue())
                .average()
                .orElse(avaliacaoDto.getNota().doubleValue());

        BigDecimal mediaFormatada = BigDecimal.valueOf(media).setScale(2, RoundingMode.HALF_UP);

        profissional.setAvaliacao(mediaFormatada);
        repositoryProfissional.save(profissional);
    }

    public LocalDateTime dataAtual() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

}
