package com.inventario.inventarioapp.mapper;

import com.inventario.inventarioapp.dto.ClientDto;
import com.inventario.inventarioapp.entity.Client;

public class ClientMapper {

    public static ClientDto mapToClientDto(Client client){
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .dni(client.getDni())
                .phoneNumber(client.getPhoneNumber())
                .address(client.getAddress())
                .build();
    }

    public static Client mapToClient(ClientDto clientDto){
        return Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .dni(clientDto.getDni())
                .phoneNumber(clientDto.getPhoneNumber())
                .address(clientDto.getAddress())
                .build();
    }
}
