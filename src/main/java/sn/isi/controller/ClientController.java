package sn.isi.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.Client;
import sn.isi.service.ClientServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private ClientServiceImpl clientServiceImpl;

    @GetMapping
    public List<Client> getClients() {
        return clientServiceImpl.getClients();
    }

    @GetMapping("{id}")
    public Client getClient(@PathVariable("id") int id) {
        return clientServiceImpl.getClient(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody Client client) {
        return clientServiceImpl.createClient(client);
    }

    @PutMapping("{id}")
    public Client updateClient(@PathVariable("id") int id, @Valid @RequestBody Client client) {
        return clientServiceImpl.updateClient(id, client);
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable("id") int id) {
        clientServiceImpl.deleteClient(id);
    }
}
