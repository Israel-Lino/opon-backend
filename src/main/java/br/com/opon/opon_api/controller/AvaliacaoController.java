package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.dto.AvaliacaoDTO;
import br.com.opon.opon_api.model.Avaliacao;
import br.com.opon.opon_api.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todas as Avaliações")
    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoService.listarAvaliacoes());
    }

    @ApiResponse(responseCode = "200", description = "Retorna uma Avaliação pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarAvaliacao(@PathVariable Integer id) {
        return ResponseEntity.ok(avaliacaoService.buscarAvaliacao(id));
    }

    @ApiResponse(responseCode = "201", description = "Cria uma nova Avaliação")
    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.criarAvaliacao(avaliacaoDto));
    }

    @ApiResponse(responseCode = "201", description = "Edita uma avaliação existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> editarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDto, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.editarAvaliacao(avaliacaoDto, id));
    }

    @ApiResponse(responseCode = "200", description = "Deleta uma Avaliação pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarAvaliacao(@PathVariable Integer id) {
        return ResponseEntity.ok(avaliacaoService.deletarAvaliacao(id));
    }

//    @GetMapping("/{id}")
//    public Profissional avaliacoesProfissional(@PathVariable Integer id){
//        return avaliacaoService.atualizarNotaProfissional(id);
//    }

}
