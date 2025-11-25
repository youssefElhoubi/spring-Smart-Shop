package com.smartshop.dto.client;

import com.smartshop.enums.CustomerTier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateDTO {
    @Size(min = 3, max = 100, message = "Client name must be between 3 and 100 characters.")
    private String name;

    @Email(message = "Email must be a valid format.")
    private String email;
}
