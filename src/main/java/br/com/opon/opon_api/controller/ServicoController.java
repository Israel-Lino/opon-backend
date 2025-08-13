package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.dto.ServicoDTO;
import br.com.opon.opon_api.model.Servico;
import br.com.opon.opon_api.service.ServicoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService){
        this.servicoService = servicoService;
    }

    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todos os Servicos")
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos(){
        return ResponseEntity.ok(servicoService.listarServicos());
    }

    @ApiResponse(responseCode = "200", description = "Retorna um Servico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarServico(@PathVariable Integer id){
        return ResponseEntity.ok(servicoService.buscarServico(id));
    }

    @ApiResponse(responseCode = "201", description = "Cadastra um novo Servico")
    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody ServicoDTO servicoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criarServico(servicoDto));
    }

    @ApiResponse(responseCode = "200", description = "Edita um Servico existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Servico> editarServico(@RequestBody ServicoDTO servicoDto, @PathVariable Integer id){
        return ResponseEntity.ok(servicoService.editarServico(servicoDto, id));
    }

    @ApiResponse(responseCode = "200", description = "Deleta um Servico pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarServico(@PathVariable Integer id){
        return ResponseEntity.ok(servicoService.excluirServico(id));
    }


}
