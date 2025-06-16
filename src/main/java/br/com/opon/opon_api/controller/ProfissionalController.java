package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.service.ProfissionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/profissional")
public class ProfissionalController {


    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService){
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> listarProfissionais(){
        return ResponseEntity.ok(profissionalService.listarProfissionais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarProfissional(@PathVariable Integer id){
        return ResponseEntity.ok(profissionalService.buscarProfissional(id));
    }

    @PostMapping
    public ResponseEntity<Profissional> cadastrarProfissional(@RequestBody Profissional profissional){
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalService.criarProfissional(profissional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> editarProfissional(@RequestBody Profissional profissional, @PathVariable Integer id) {
        profissional.setIdProfissional(id);
        return ResponseEntity.ok(profissionalService.editarProfissional(profissional, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarProfissional(@PathVariable Integer id){
        profissionalService.excluirProfissional(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
