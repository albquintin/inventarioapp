package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> findAllClients();

    void createClient(ClientDto clientDto);

    void updateClient(ClientDto clientDto);

    ClientDto findClientById(Long clientId);

    void deleteClient(Long clientId);
}
