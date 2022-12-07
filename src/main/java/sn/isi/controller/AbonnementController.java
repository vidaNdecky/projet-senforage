package sn.isi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.Abonnement;
import sn.isi.service.AbonnementServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/abonnements")
@AllArgsConstructor
public class AbonnementController {
    private AbonnementServiceImpl abonnementServiceImpl;

    @GetMapping
    public List<Abonnement> getAbonnements() {
        return abonnementServiceImpl.getAbonnements();
    }

    @GetMapping("{id}")
    public Abonnement getAbonnement(@PathVariable("id") int id) {
        return abonnementServiceImpl.getAbonnement(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Abonnement createAbonnement(@Valid @RequestBody Abonnement abonnement) {
        return abonnementServiceImpl.createAbonnement(abonnement);
    }

    @PutMapping("{id}")
    public Abonnement updateAbonnement(@PathVariable("id") int id, @Valid @RequestBody Abonnement abonnement) {
        return abonnementServiceImpl.updateAbonnement(id, abonnement);
    }

    @DeleteMapping("{id}")
    public void deleteAbonnement(@PathVariable("id") int id) {
        abonnementServiceImpl.deleteAbonnement(id);
    }
}
