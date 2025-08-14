package br.com.opon.opon_api.controller;

import br.com.opon.opon_api.model.Cliente;
import br.com.opon.opon_api.service.ClienteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
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

    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todos os Clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.status(200).body(clienteService.listarClientes());
    }

    @ApiResponse(responseCode = "200", description = "Retorna um Cliente pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }

    @ApiResponse(responseCode = "200", description = "Verifica se o cliente existe no banco")
    @PostMapping("/validar")
    public ResponseEntity<Cliente> validarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.validarCliente(cliente));
    }

    @ApiResponse(responseCode = "201", description = "Cadastra um novo Cliente")
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(cliente));
    }

    @ApiResponse(responseCode = "201", description = "Edita um cliente existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente, @PathVariable Integer id) {
        cliente.setIdCliente(id);
        return ResponseEntity.ok(clienteService.editarCliente(cliente, id));
    }

    @ApiResponse(responseCode = "200", description = "Deleta um Cliente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.excluirCliente(id));
    }
}
