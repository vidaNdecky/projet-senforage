package sn.isi.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sn.isi.dto.Abonnement;

import java.util.List;


@SpringBootTest
class AbonnementServiceImplTest {
    @Autowired
    private IAbonnementService abonnementService;

    @Test
    void getAbonnements() {
        List<Abonnement> abonnementList =
                abonnementService.getAbonnements();
        Assertions.assertEquals(1, abonnementList.size());
    }

    @Test
    void getAbonnement() {
        Abonnement abonnement = abonnementService.getAbonnement(1);

        Assertions.assertNotNull(abonnement);
    }

    @Test
    void createAbonnement() {

        Abonnement abonnement = new Abonnement();
        abonnement.setDateAbonnement("3/12/2022");
        abonnement.setCumulConsomation(12500);
        abonnement.setDescription("Ceci est un abonnement lié à un compteur");
        abonnement.setNumCompteur(1);

        Abonnement abonnementSave = abonnementService.createAbonnement(abonnement);

        Assertions.assertNotNull(abonnementSave);
        //Assertions.assertEquals(abonnement.getNom(), abonnementSave.getNom());
    }

    @Test
    void updateAbonnement() {
        Abonnement abonnement = new Abonnement();
        abonnement.setCumulConsomation(25000);

        Abonnement abonnementSave = abonnementService.updateAbonnement(3, abonnement);

        Assertions.assertEquals(25000, abonnementSave.getCumulConsomation());
    }

    @Test
    void deleteAbonnement() {
        abonnementService.deleteAbonnement(3);
        Assertions.assertTrue(true);
    }
}
