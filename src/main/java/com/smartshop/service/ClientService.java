package com.smartshop.service;

import com.smartshop.dto.client.ClientResponseDTO;
import com.smartshop.dto.client.CreateClientDTO;
import com.smartshop.mapper.ClientMapper;
import com.smartshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientService clientService;
    private ClientMapper clientMapper;
    private UserRepository userRepository;

    public ClientService(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }
    public ClientResponseDTO createClient(CreateClientDTO dto){

        return null;
    }
}
