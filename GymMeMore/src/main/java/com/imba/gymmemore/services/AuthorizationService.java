package com.imba.gymmemore.services;

import com.imba.gymmemore.DTO.ClientDTO;
import com.imba.gymmemore.models.Client;
import com.imba.gymmemore.models.Person;
import com.imba.gymmemore.repositories.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final ClientRepository clientRepository;

    public AuthorizationService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO addClient(ClientDTO clientDTO){
        Client client = clientRepository.save(new Client(clientDTO.getFirstName(), clientDTO.getEmail(),
                clientDTO.getUsername(), clientDTO.getPassword()));
        return new ClientDTO();
    }
}
