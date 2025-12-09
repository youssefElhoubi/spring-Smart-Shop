package com.smartshop.controller;

import com.smartshop.dto.client.ClientResponseDTO;
import com.smartshop.dto.client.ClientUpdateDTO;
import com.smartshop.dto.client.CreateClientDTO;
import com.smartshop.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("list")
    public ResponseEntity<List<ClientResponseDTO>> listClient() {
        List<ClientResponseDTO> clients = clientService.listClients();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/create")
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody CreateClientDTO request) {
        ClientResponseDTO response = clientService.createClient(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> info(@PathVariable Long id) {
        ClientResponseDTO response = clientService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid ClientUpdateDTO request) {
        ClientResponseDTO response = clientService.updateClient(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok("client with ID " + id + "was deleted");
    }

}
