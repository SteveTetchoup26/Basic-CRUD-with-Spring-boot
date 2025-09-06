package com.st.sa.controllers;


import com.st.sa.dto.ClientDTO;
import com.st.sa.dto.ErrorEntity;
import com.st.sa.entities.Client;
import com.st.sa.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

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
    public Stream<ClientDTO> getClients() {
        return this.clientService.getClients();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    // Error handling

    // first method try and catch
//    public ResponseEntity getClient(@PathVariable int id) {
//        try {
//            Client client = this.clientService.getClient(id);
//            return ResponseEntity.ok(client);
//        } catch (EntityNotFoundException exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity(null, exception.getMessage()));
//        }
//    }

    // second method exception handler
    public Client lire(@PathVariable int id) {
        return this.clientService.getClient(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorEntity handleException(EntityNotFoundException exception) {
        return new ErrorEntity(null, exception.getMessage());
    }

    // third method controller advice; see package advice

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Client client) {
        this.clientService.update(id, client);
    }
}
