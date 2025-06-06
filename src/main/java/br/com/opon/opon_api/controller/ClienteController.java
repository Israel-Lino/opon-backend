package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.service.ClienteService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.status(200).body(clienteService.listarClientes());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(clienteService.criarCliente(cliente));
    }

    @PutMapping("/editar")
    //Terminar funcionalidade de edição
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.status(201).body(clienteService.editarCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarCliente(@PathVariable Integer id){
        clienteService.excluirCliente(id);
        return ResponseEntity.status(204).body(true);
    }
}
