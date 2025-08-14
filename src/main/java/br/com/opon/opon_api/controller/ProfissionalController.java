package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.model.Profissional;
import br.com.opon.opon_api.service.ProfissionalService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todos os Profissionais")
    @GetMapping
    public ResponseEntity<List<Profissional>> listarProfissionais(){
        return ResponseEntity.ok(profissionalService.listarProfissionais());
    }

    @ApiResponse(responseCode = "200", description = "Retorna um Profissional pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarProfissional(@PathVariable Integer id){
        return ResponseEntity.ok(profissionalService.buscarProfissional(id));
    }

    @ApiResponse(responseCode = "200", description = "Verifica se o profissional existe no banco")
    @PostMapping("/validar")
    public ResponseEntity<Profissional> validarCliente(@RequestBody Profissional profissional) {
        return ResponseEntity.ok(profissionalService.validarProfissional(profissional));
    }

    @ApiResponse(responseCode = "201", description = "Cadastra um novo Profissional")
    @PostMapping
    public ResponseEntity<Profissional> cadastrarProfissional(@RequestBody Profissional profissional){
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalService.criarProfissional(profissional));
    }

    @ApiResponse(responseCode = "201", description = "Edita um Profissional existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Profissional> editarProfissional(@RequestBody Profissional profissional, @PathVariable Integer id) {
        profissional.setIdProfissional(id);
        return ResponseEntity.ok(profissionalService.editarProfissional(profissional, id));
    }

    @ApiResponse(responseCode = "200", description = "Deleta um Profissional pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarProfissional(@PathVariable Integer id){
        return ResponseEntity.ok(profissionalService.excluirProfissional(id));
    }

}
