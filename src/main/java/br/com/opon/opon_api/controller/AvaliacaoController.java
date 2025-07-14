package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.dto.AvaliacaoDTO;
import br.com.opon.opon_api.model.Avaliacao;
import br.com.opon.opon_api.service.AvaliacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/avaliacao")
@AllArgsConstructor
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoService.listarAvaliacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarAvaliacao(@PathVariable Integer id) {
        return ResponseEntity.ok(avaliacaoService.buscarAvaliacao(id));
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.criarAvaliacao(avaliacaoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> editarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDto, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.editarAvaliacao(avaliacaoDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarAvaliacao(@PathVariable Integer id) {
        return ResponseEntity.ok(avaliacaoService.deletarAvaliacao(id));
    }

}
