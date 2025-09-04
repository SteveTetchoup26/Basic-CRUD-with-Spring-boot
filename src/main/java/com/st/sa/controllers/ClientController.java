package com.st.sa.controllers;


import com.st.sa.entities.Client;
import com.st.sa.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createClient(@RequestBody Client client){
        this.clientService.createClient(client);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Client> getClients() {
        return this.clientService.getClients();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Client getClient(@PathVariable int id) {
        return this.clientService.getClient(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Client client) {
        this.clientService.update(id, client);
    }
}
