package com.smartshop.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientDTO {

    // Must link to an existing User entity (authentication details)
    @NotNull(message = "User ID must be provided to link client profile.")
    private Long userId; // Renamed from 'user' to 'userId' for clarity

    @NotBlank(message = "Client name cannot be empty.")
    private String name;

    @NotBlank(message = "Email address is required.")
    @Email(message = "Email must be a valid format.")
    private String email;
}