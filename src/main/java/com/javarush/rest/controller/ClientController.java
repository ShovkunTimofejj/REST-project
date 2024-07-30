package com.javarush.rest.controller;

import com.javarush.rest.model.Client;
import com.javarush.rest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails.Node.Protocol.HTTP;
import static org.springframework.util.Assert.notNull;

@RestController
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
     //Method for creating a new client
    @PostMapping(value ="/clients")
    public ResponseEntity<?> create(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Method for getting client
    @GetMapping(value ="/clients")
    public ResponseEntity<List<Client>> read (){
       final List<Client> clients = clientService.readAll();

       if (clients != null && !clients.isEmpty()) {
           return new ResponseEntity<>(clients, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Method for getting client by ID
    @GetMapping(value ="/clients/{id}")
    public ResponseEntity<Client> read (@PathVariable(name = "id") int id){
        final Client client = clientService.read(id);

        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Method for updating client by ID
    @PutMapping(value ="/clients/{id}")
    public ResponseEntity<?> update (@PathVariable(name = "id") int id, @RequestBody Client client){
       final boolean update = clientService.update(client, id);

        if (update) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //Method for deleting client by ID
    @DeleteMapping(value ="/clients/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") int id){
        final boolean delete = clientService.delete(id);

        if (delete) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
