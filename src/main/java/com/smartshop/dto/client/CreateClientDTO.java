package com.smartshop.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientDTO {

    @NotNull(message = "User ID must be provided to link client profile.")
    private Long userId;

    @NotBlank(message = "Client name cannot be empty.")
    @Size(min = 3, max = 100, message = "Client name must be between 3 and 100 characters.")
    private String name;

    @NotBlank(message = "Email address is required.")
    @Email(message = "Email must be a valid format.")
    private String email;
}