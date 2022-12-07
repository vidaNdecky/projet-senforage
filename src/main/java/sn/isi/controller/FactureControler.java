package sn.isi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.Facture;
import sn.isi.service.FactureServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/factures")
@AllArgsConstructor
public class FactureControler {

    private FactureServiceImpl factureServiceImpl;
    @GetMapping
    public List<Facture> getFactures() {
        return factureServiceImpl.getFactures();
    }

    @GetMapping("{id}")
    public Facture getFacture(@PathVariable("id") int id) {
        return factureServiceImpl.getFacture(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Facture createFacture(@Valid @RequestBody Facture facture) {
        return factureServiceImpl.createFacture(facture);
    }

    @PutMapping("{id}")
    public Facture updateFacture(@PathVariable("id") int id, @Valid @RequestBody Facture facture) {
        return factureServiceImpl.updateFacture(id, facture);
    }

    @DeleteMapping("{id}")
    public void deleteFacture(@PathVariable("id") int id) {
        factureServiceImpl.deleteFacture(id);
    }
}
