package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numFacture;

    @Column(length = 150, nullable = false)
    private String consoLettre;

    @Column(length = 150, nullable = false)
    private double consoChiffre;

    @Column(length = 150, nullable = false)
    private double prix;

    @ManyToOne
    private AbonnementEntity abonnement;
}
