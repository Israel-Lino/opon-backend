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

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> listarProfissionais() {
        return ResponseEntity.status(200).body(profissionalService.listarProfissionais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarProfissionalID(@PathVariable Integer id) {
        return ResponseEntity.ok(profissionalService.buscarProfissionalID(id));
    }

    @PostMapping
    public ResponseEntity<Profissional> cadastrarProfissional(@RequestBody Profissional profissional) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.profissionalService.criarProfissional(profissional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> editarProfissional(@PathVariable Integer id, @RequestBody Profissional profissional) {
        profissional.setId(id);
        return ResponseEntity.ok(profissionalService.editarProfissional(id, profissional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profissional> deletarProfissional(@PathVariable Integer id) {
        profissionalService.deletarProfissional(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
