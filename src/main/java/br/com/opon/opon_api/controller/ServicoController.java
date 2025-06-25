package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.dto.ServicoDTO;
import br.com.opon.opon_api.model.Servico;
import br.com.opon.opon_api.service.ServicoService;
import org.apache.coyote.Response;
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

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos(){
        return ResponseEntity.ok(servicoService.listarServicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarServico(@PathVariable Integer id){
        return ResponseEntity.ok(servicoService.buscarServico(id));
    }

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody ServicoDTO servicoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criarServico(servicoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> editarServico(@RequestBody ServicoDTO servicoDto, @PathVariable Integer id){
        return ResponseEntity.ok(servicoService.editarServico(servicoDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarServico(@PathVariable Integer id){
        return ResponseEntity.ok(servicoService.excluirServico(id));
    }


}
