package com.smartshop.dto.user;

import com.smartshop.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateUserDto {
    @NotEmpty(message = "username should not be empty")
    @NotBlank(message = "username should not be blank")
    private String Username;
    @NotEmpty(message = "password should not be empty")
    @NotBlank(message = "password should not be blank")
    private String password;
    private Role role;
}
