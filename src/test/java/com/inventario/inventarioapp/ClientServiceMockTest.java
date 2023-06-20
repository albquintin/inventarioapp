package com.inventario.inventarioapp;

import com.inventario.inventarioapp.entity.Client;
import com.inventario.inventarioapp.mapper.ClientMapper;
import com.inventario.inventarioapp.repository.ClientRepository;
import com.inventario.inventarioapp.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceMockTest {
    @MockBean
    private ClientRepository clientRepository;
    @Autowired
    private ClientServiceImpl clientService;
    List<Client> oldClientList;
    List<Client> activeClientList;
    Client firstClient;
    Client secondClient;
    Client thirdClient;

    @BeforeEach
    public void beforeEach(){
        oldClientList = new ArrayList<>();
        activeClientList = new ArrayList<>();
        firstClient = new Client(1L, "Client", "prueba@email.com", "12345678A", "123464545", "Calle Avenida" , true);
        secondClient = new Client(2L, "Client2", "prueba2@email.com", "32145678A", "232464545", "Calle Plaza" , true);
        thirdClient = new Client(3L, "Client3", "prueba3@email.com", "22245678A", "111464545", "Calle Carrera" , false);
    }

    @Test
    public void findActiveClients(){
        activeClientList.add(firstClient);
        activeClientList.add(secondClient);

        when(clientRepository.findActiveClients()).thenReturn(activeClientList);
        assertEquals(activeClientList.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList()), clientService.findActiveClients());
    }
    @Test
    public void findOldClients(){
        oldClientList.add(thirdClient);

        when(clientRepository.findOldClients()).thenReturn(oldClientList);
        assertEquals(oldClientList.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList()), clientService.findOldClients());
    }
}
