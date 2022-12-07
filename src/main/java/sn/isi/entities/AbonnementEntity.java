package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbonnementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroAbonnement;

    @Column(length = 20, nullable = false)
    private String dateAbonnement;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(length = 150, nullable = false)
    private int numCompteur;

    @Column(length = 150, nullable = false)
    private double cumulConsomation;

    @ManyToOne
    private ClientEntity client;
}
