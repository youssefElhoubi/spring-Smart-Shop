package com.smartshop.controller;

import com.smartshop.dto.client.ClientResponseDTO;
import com.smartshop.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("List")
    public ResponseEntity<List<ClientResponseDTO>> listClient(){
        List<ClientResponseDTO> clients = clientService.listClients();
        return ResponseEntity.ok(clients);
    }
}
