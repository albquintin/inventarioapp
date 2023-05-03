package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.ClientDto;
import com.inventario.inventarioapp.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/clients")
    public String clients(Model model){
        List<ClientDto> clients = clientService.findActiveClients();
        model.addAttribute("clients", clients);
        return "/clients/clients";
    }
    @GetMapping("/clients/old_clients")
    public String oldClients(Model model){
        List<ClientDto> clients = clientService.findOldClients();
        model.addAttribute("clients", clients);
        return "/clients/old_clients";
    }

    @GetMapping("/clients/clients/newclient")
    public String newClientForm(Model model){
        ClientDto clientDto = new ClientDto();
        model.addAttribute("client", clientDto);
        return "/clients/create_client";
    }

    @PostMapping("clients/clients")
    public String createClient(@Valid @ModelAttribute("client") ClientDto clientDto,
                               BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("client", clientDto);
            return "clients/create_client";
        }
        clientDto.setActive(true);
        clientService.createClient(clientDto);
        return "redirect:/clients/clients";
    }

    @GetMapping("clients/clients/edit/{clientId}")
    public String editClientForm(@PathVariable("clientId") Long clientId, Model model){
        ClientDto clientDto = clientService.findClientById(clientId);
        model.addAttribute("client", clientDto);
        return "clients/edit_client";
    }

    @PostMapping("clients/clients/{clientId}")
    public String updateClient(@PathVariable("clientId") Long clientId,
                               @Valid @ModelAttribute("client") ClientDto client,
                               BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("client", client);
            return "clients/edit_client";
        }
        client.setId(clientId);
        clientService.updateClient(client);
        return "redirect:/clients/clients";
    }

    @GetMapping("clients/clients/delete/{clientId}")
    public String deleteClient(@PathVariable("clientId") Long clientId, Model model){
        clientService.logicDeleteClient(clientId);
        return "redirect:/clients/clients";
    }

    @GetMapping("clients/old_clients/restore/{clientId}")
    public String restoreClient(@PathVariable("clientId") Long clientId, Model model){
        clientService.restoreClient(clientId);
        return "redirect:/clients/old_clients";
    }

    @GetMapping("clients/old_clients/delete/{clientId}")
    public String deleteOldClient(@PathVariable("clientId") Long clientId, Model model){
        clientService.deleteClient(clientId);
        return "redirect:/clients/old_clients";
    }
}
