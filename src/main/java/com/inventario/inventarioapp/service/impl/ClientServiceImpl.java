package com.inventario.inventarioapp.service.impl;

import com.inventario.inventarioapp.dto.ClientDto;
import com.inventario.inventarioapp.entity.Client;
import com.inventario.inventarioapp.mapper.ClientMapper;
import com.inventario.inventarioapp.repository.ClientRepository;
import com.inventario.inventarioapp.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList());
    }

    @Override
    public void createClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        clientRepository.save(client);
    }

    @Override
    public void updateClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        clientRepository.save(client);
    }

    @Override
    public ClientDto findClientById(Long clientId) {
        Client client = clientRepository.findById(clientId).get();
        return ClientMapper.mapToClientDto(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
