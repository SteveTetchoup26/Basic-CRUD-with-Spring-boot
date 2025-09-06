package com.st.sa.services;

import com.st.sa.entities.Client;
import com.st.sa.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
//    Injection de dépendances méthode 1
//    @Autowired
//    ClientRepository clientRepository;

//    Injection de dépendances méthode 2 et recommandée

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Client client){
        Client clt = this.clientRepository.findByEmail(client.getEmail());

        if(clt == null) {
            this.clientRepository.save(client);
        }
    }

    public List<Client> getClients() {
        return this.clientRepository.findAll();
    }

    public Client getClient(int id) {
        Optional<Client> optionalClient = this.clientRepository.findById(id);

//        if(optionalClient.isPresent()) {
//            return optionalClient.get();
//        }
//
//        return null;

//        return optionalClient.orElse(null);

        return optionalClient.orElseThrow(
                () -> new EntityNotFoundException("Mauvaise requete")
        );
    }

    public Client getOrCreate(Client client) {
        Client clt = this.clientRepository.findByEmail(client.getEmail());

        if(clt == null) {
            clt = this.clientRepository.save(client);
        }

        return clt;
    }

    public void update(int id, Client client) {

        Client clt = this.getClient(id);

        if(clt.getId() == client.getId()) {
            clt.setEmail(client.getEmail());
            clt.setTelephone(client.getTelephone());

            this.clientRepository.save(clt);
        }
    }
}
