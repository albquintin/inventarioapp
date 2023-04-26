package com.inventario.inventarioapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long id;
    @NotEmpty(message = "El nombre del cliente no puede estar vacío")
    private String name;
    @NotEmpty(message = "El email del cliente no puede estar vacío")
    @Email
    private String email;
    @NotEmpty(message = "El dni del cliente no puede estar vacío")
    private String dni;
    @NotEmpty(message = "El móvil del cliente no puede estar vacío")
    private String phoneNumber;
    @NotEmpty(message = "La dirección del cliente no puede estar vacía")
    private String address;
}
