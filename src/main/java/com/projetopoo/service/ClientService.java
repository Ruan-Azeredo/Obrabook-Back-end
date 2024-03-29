package com.projetopoo.service;

import com.projetopoo.document.Client;
import com.projetopoo.document.User;
import com.projetopoo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = Client.SEQUENCE_NAME;

    @Autowired
    private ConstructionService constructionService;

    private UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        constructionService.setClientService(this);
    }

    public Client create(Client client) throws Exception{
        List<Client> existingClient = showClientByCPF(client.getCpf());

        if (existingClient.isEmpty()){
            User updateUser = userService.showUser(client.getUserID());

            if (existingClient.isEmpty()){
                client.setId(idService.getSequenceNumber(SEQUENCE_NAME));

                updateUser.setTypeOfUser("Cliente");
                updateUser.setEngOrCliID(client.getId());

                userService.update(updateUser, true);

                return repository.save(client);
            } else {
                throw new Exception("A engineer/client is already set to this User.");
            }
        } else {
            return existingClient.get(0);
        }
    }

    public List<Client> showClientByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Client> showClients(){
        return repository.findAll();
    }

    public Client showClient(long clientID){
        return repository.findById(clientID).get();
    }

    public Client update(Client client){
        Client existingClient = showClient(client.getId());

        existingClient.setCpf(client.getCpf());
        existingClient.setName(client.getName());
        existingClient.setPhoneNo(client.getPhoneNo());

        return repository.save(existingClient);
    }

    public String delete(long clientID){
        Client clientTBD = showClient(clientID);

        String msg = userService.deleteUser(clientTBD.getUserID());

        repository.deleteById(clientID);

        return msg + "\n" + "The client with the ID " + clientID + " was deleted!";
    }

    public User getUser(long clientID){
        return userService.showUser(showClient(clientID).getUserID());
    }
}
