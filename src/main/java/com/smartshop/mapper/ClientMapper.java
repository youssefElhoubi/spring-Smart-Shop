package com.smartshop.mapper;

import com.smartshop.dto.client.CreateClientDTO;
import com.smartshop.dto.client.ClientResponseDTO;
import com.smartshop.entity.Client;
import com.smartshop.entity.User;
import com.smartshop.enums.CustomerTier; // Need this import

import org.springframework.stereotype.Component;
import java.util.Optional; // Need this import for safe handling

/**
 * Manual implementation of the Client Mapper.
 * This ensures Spring finds the component and bypasses MapStruct build issues.
 */
@Component
public class ClientMapper {

    public ClientResponseDTO toResponseDTO(Client client) {
        if (client == null) {
            return null;
        }

        return ClientResponseDTO.builder()
                .id(client.getUser().getId())
                .name(client.getName())
                .email(client.getEmail())
                .tier(client.getTier())
                .totalOrders(client.getTotalOrders())
                .totalSpent(client.getTotalSpent())
                .build();
    }

    public Client toNewEntity(CreateClientDTO dto) {
        if (dto == null) {
            return null;
        }

        return Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }


    public void updateEntityWithUser(Client client, User user) {
        if (client == null || user == null) {
            return;
        }
        
        client.setId(user.getId());


        client.setUser(user);

    }
}