package com.smartshop.service;

import com.smartshop.dto.client.ClientResponseDTO;
import com.smartshop.dto.client.ClientUpdateDTO;
import com.smartshop.dto.client.CreateClientDTO;
import com.smartshop.entity.Client;
import com.smartshop.entity.User;
import com.smartshop.exeptions.BusinessRuleViolationException;
import com.smartshop.mapper.ClientMapper;
import com.smartshop.repository.ClientRepository;
import com.smartshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.userRepository = userRepository;
    }

    public ClientResponseDTO createClient(CreateClientDTO dto) throws BusinessRuleViolationException {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> {
            new BusinessRuleViolationException("user does not exist");
            return null;
        });

        Client client = clientMapper.toNewEntity(dto);
        client.setUser(user);

        clientRepository.save(client);

        return clientMapper.toResponseDTO(client);
    }

    public ClientResponseDTO updateClient(ClientUpdateDTO dto, Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> {
            new BusinessRuleViolationException("client was not found");
            return null;
        });
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());

        client = clientRepository.save(client);

        return clientMapper.toResponseDTO(client);
    }

    public ClientResponseDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> {
            new BusinessRuleViolationException("client was not found");
            return null;
        });

        return clientMapper.toResponseDTO(client);
    }

}
