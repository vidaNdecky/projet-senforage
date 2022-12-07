package sn.isi.service;

import sn.isi.dto.Client;

import java.util.List;

public interface IClientService {
    public Client createClient(Client client);
    public Client updateClient(int id, Client client);
    public void deleteClient(int id);
    public Client getClient(int id);
    public List<Client> getClients();
}
